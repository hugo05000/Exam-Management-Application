import requests

BASE_URL = "http://localhost:8080/api"

def teacher_login():
    """
    Simule la connexion d'un enseignant.
    Dans un contexte réel, il s'agirait d'une authentification (token, session, etc.).
    """
    teacher = {
        "id": 1,
        "firstName": "Hugo",
        "lastName": "Marceau",
        "email": "hugo@example.com",
        "username": "hugo",
        "role": "TEACHER"
    }
    print("Enseignant connecté :", teacher["username"])
    return teacher

def get_quiz_listing():
    """
    Récupère et affiche la liste des quiz existants.
    """
    url = f"{BASE_URL}/quizs"
    response = requests.get(url)
    if response.status_code == 200:
        quizzes = response.json()
        print("Liste des quizs :")
        for quiz in quizzes:
            print(f" - {quiz['title']} (ID : {quiz['id']})")
        return quizzes
    else:
        print("Erreur lors de la récupération des quizs :", response.text)
        return []

def create_quiz():
    """
    Crée un nouveau quiz en envoyant une requête POST sur l'endpoint dédié.
    """
    quiz_data = {
        "title": "Quiz d'examen de Mathématiques",
        "questions": []
    }
    url = f"{BASE_URL}/quizs"
    response = requests.post(url, json=quiz_data)
    if response.status_code == 201:
        quiz = response.json()
        print("Quiz créé avec succès :", quiz['title'], "(ID :", quiz['id'],")")
        return quiz
    else:
        print("Erreur lors de la création du quiz :", response.text)
        return None

def create_question(quiz_id, exam_id, question_title, options, right_answer, category="Mathématiques", difficulty="FACILE"):
    """
    Crée une question en l'associant à un quiz et à un examen.
    Le JSON envoyé inclut :
      - les détails de la question
      - l'examen auquel la question doit être rattachée
      - la liste des quiz dans lesquels la question doit apparaître
    """
    question_data = {
        "questionTitle": question_title,
        "option_1": options[0],
        "option_2": options[1],
        "option_3": options[2],
        "option_4": options[3],
        "rightAnswer": right_answer,
        "category": category,
        "difficultyLevel": difficulty,
        "exam": {"id": exam_id},
        "quizzes": [{"id": quiz_id}]
    }
    url = f"{BASE_URL}/questions"
    response = requests.post(url, json=question_data)
    if response.status_code == 201:
        question = response.json()
        print("Question créée :", question['questionTitle'], "(ID :", question['id'],")")
        return question
    else:
        print("Erreur lors de la création de la question :", response.text)
        return None

def get_quiz_by_id(quiz_id):
    """
    Récupère les détails d'un quiz donné, notamment la liste de ses questions.
    """
    url = f"{BASE_URL}/quizs/{quiz_id}"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        print("Erreur lors de la récupération du quiz :", response.text)
        return None

def attach_quiz_to_exam(quiz, exam_id):
    """
    Rattache le quiz à un examen en mettant à jour la liste des questions de l'examen.
    Cette fonction récupère d'abord l'examen, ajoute (sans doublon) les questions du quiz à l'attribut 'questions'
    de l'examen, puis effectue une mise à jour via l'endpoint PUT.
    """
    exam_url = f"{BASE_URL}/exams/{exam_id}"
    exam_response = requests.get(exam_url)
    if exam_response.status_code != 200:
        print("Erreur lors de la récupération de l'examen :", exam_response.text)
        return

    exam = exam_response.json()
    quiz_details = get_quiz_by_id(quiz["id"])
    if quiz_details is None:
        return

    # Fusionner les questions de l'examen et du quiz en évitant les doublons
    exam_questions = exam.get("questions", [])
    quiz_questions = quiz_details.get("questions", [])
    # On utilise les identifiants pour éviter les répétitions
    existing_ids = {q["id"] for q in exam_questions if "id" in q}
    for question in quiz_questions:
        if question.get("id") not in existing_ids:
            exam_questions.append(question)
    exam["questions"] = exam_questions

    update_response = requests.put(exam_url, json=exam)
    if update_response.status_code == 200:
        updated_exam = update_response.json()
        print("Quiz rattaché à l'examen avec succès. Examen mis à jour.")
        return updated_exam
    else:
        print("Erreur lors de l'attachement du quiz à l'examen :", update_response.text)
        return None

def main():
    # Étape 1 : L'enseignant se connecte
    teacher = teacher_login()

    # Étape 2 : Accès à la section "Gérer mes quizs"
    print("\nAccès à la section 'Gérer mes quizs'")
    get_quiz_listing()

    # Étape 3 : Création d'un nouveau quiz
    print("\nCréation d'un nouveau quiz")
    quiz = create_quiz()
    if quiz is None:
        return

    # Pour ce scénario, nous supposons que l'examen concerné existe déjà (ici, examen d'ID 1)
    exam_id = 1

    # Création de plusieurs questions et association au quiz et à l'examen
    print("\nCréation de questions associées au quiz")
    create_question(
        quiz_id=quiz["id"],
        exam_id=exam_id,
        question_title="Quelle est la dérivée de x² ?",
        options=["2x", "x", "x²", "1"],
        right_answer="2x"
    )

    create_question(
        quiz_id=quiz["id"],
        exam_id=exam_id,
        question_title="Combien font 2+2 ?",
        options=["3", "4", "5", "22"],
        right_answer="4"
    )

    # Étape 4 : Rattacher le quiz à l'examen (mise à jour de l'examen avec les questions du quiz)
    print("\nRattachement du quiz à l'examen")
    attach_quiz_to_exam(quiz, exam_id)

if __name__ == "__main__":
    main()