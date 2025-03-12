import requests

BASE_URL = "http://localhost:8080/api"

def teacher_login():
    """
    Simule la connexion d'un enseignant.
    Dans un cas réel, cette étape impliquerait une authentification avec vérification des identifiants.
    Ici, nous simulons simplement la connexion en renvoyant un dictionnaire représentant l'enseignant.
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

def get_exam_listing():
    """
    Récupère et affiche la liste des examens enregistrés.
    Cette fonction permet à la fois à l'administration et aux étudiants de consulter les examens.
    """
    url = f"{BASE_URL}/exams"
    response = requests.get(url)
    if response.status_code == 200:
        exams = response.json()
        print("Liste des examens :")
        for exam in exams:
            print(f" - {exam['examTitle']} (ID : {exam['id']})")
        return exams
    else:
        print("Erreur lors de la récupération des examens :", response.text)
        return []

def create_exam(teacher):
    """
    Crée un nouvel examen.
    L'enseignant sélectionne (ici simulée) la classe concernée et remplit un formulaire d'examen.
    Le champ 'teacher' est renseigné avec l'objet enseignant.
    """
    # Simuler la sélection d'une classe concernée par l'examen (par exemple, le cours d'ID 1)
    selected_course_id = 1  # Ce paramètre pourra servir dans une implémentation étendue
    exam_data = {
        "examTitle": "Examen de Mathématiques",
        "teacher": {
            "id": teacher["id"]
        },
        "questions": [],
        "students": []  # Initialement vide, les inscriptions se feront ultérieurement
    }
    url = f"{BASE_URL}/exams"
    response = requests.post(url, json=exam_data)
    if response.status_code == 201:
        exam = response.json()
        print(f"Examen créé avec succès : {exam['examTitle']} (ID : {exam['id']})")
        return exam
    else:
        print("Erreur lors de la création de l'examen :", response.text)
        return None

def update_exam(exam):
    """
    Permet à l'enseignant de modifier un examen existant.
    Ici, on modifie par exemple le titre de l'examen.
    """
    exam["examTitle"] = exam["examTitle"] + " - Version modifiée"
    url = f"{BASE_URL}/exams/{exam['id']}"
    response = requests.put(url, json=exam)
    if response.status_code == 200:
        updated_exam = response.json()
        print("Examen mis à jour avec succès :", updated_exam["examTitle"])
        return updated_exam
    else:
        print("Erreur lors de la mise à jour de l'examen :", response.text)
        return None

def delete_exam(exam):
    """
    Permet à l'enseignant de supprimer un examen existant.
    """
    url = f"{BASE_URL}/exams/{exam['id']}"
    response = requests.delete(url)
    if response.status_code == 200:
        print("Examen supprimé avec succès.")
    else:
        print("Erreur lors de la suppression de l'examen :", response.text)

def main():
    # Étape 1 : L'enseignant se connecte
    teacher = teacher_login()

    # Étape 2 : Accès à la section "Gestion des examens"
    print("\nAccès à la section Gestion des examens")
    get_exam_listing()

    # Étape 3 : Création d'un examen via le formulaire
    print("\nCréation d'un nouvel examen")
    exam = create_exam(teacher)

    if exam:
        # Vérifier que l'examen est bien enregistré et visible dans le listing
        print("\nListe des examens après création")
        get_exam_listing()

        # Étape 4 : L'enseignant peut modifier l'examen si nécessaire
        print("\nModification de l'examen")
        updated_exam = update_exam(exam)

        print("\nListe des examens après mise à jour")
        get_exam_listing()

        # Étape 5 (optionnelle) : L'enseignant peut supprimer l'examen
        print("\nSuppression de l'examen")
        delete_exam(updated_exam)

        print("\nListe des examens après suppression")
        get_exam_listing()

if __name__ == "__main__":
    main()