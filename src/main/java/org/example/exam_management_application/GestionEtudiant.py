import requests

BASE_URL = "http://localhost:8080/api"

def admin_login():
    """
    Simule la connexion d'un administrateur.
    """
    admin = {
        "id": 2,
        "firstName": "Admin",
        "lastName": "User",
        "email": "admin@example.com",
        "username": "admin",
        "role": "ADMIN"
    }
    print("Administrateur connecté :", admin["username"])
    return admin

def get_exam_by_id(exam_id):
    """"
    Récupère et affiche les détails d'un examen spécifique.
    """
    url = f"{BASE_URL}/exams/{exam_id}"
    response = requests.get(url)
    if response.status_code == 200:
        exam = response.json()
        print("Examen sélectionné : {exam['examTitle']} (ID : {exam['id']})")
        return exam
    else:
        print("Erreur lors de la récupération de l'examen :", response.text)
        return None

def get_all_users():
    """
    Récupère la liste de tous les utilisateurs.
    """
    url = f"{BASE_URL}/users"
    response = requests.get(url)
    if response.status_code == 200:
        users = response.json()
        print("Liste des utilisateurs récupérée.")
        return users
    else:
        print("Erreur lors de la récupération des utilisateurs :", response.text)
        return []

def assign_students_to_exam(exam_id, student_ids):
    """
    Attribue les étudiants spécifiés à l'examen.

    Pour cela, on récupère l'examen, on met à jour son attribut 'students' avec
    une liste d'objets étudiants (contenant uniquement l'ID), puis on effectue une mise à jour via PUT.
    """
    exam = get_exam_by_id(exam_id)
    if exam is None:
        return None

    # Construction de la liste des étudiants à associer
    selected_students = [{"id": sid} for sid in student_ids]
    exam["students"] = selected_students

    url = f"{BASE_URL}/exams/{exam_id}"
    response = requests.put(url, json=exam)
    if response.status_code == 200:
        updated_exam = response.json()
        print("Étudiants attribués à l'examen avec succès.")
        return updated_exam
    else:
        print("Erreur lors de l'attribution des étudiants à l'examen :", response.text)
        return None

def main():
    # Étape 1 : L'administrateur se connecte
    admin = admin_login()

    # Étape 2 : Accès à la section "Gestion des étudiants et examens"
    print("\nAccès à la section 'Gestion des étudiants et examens'")
    users = get_all_users()

    # Filtrer les utilisateurs pour obtenir uniquement les étudiants
    students = [user for user in users if user.get("role") == "STUDENT"]
    if not students:
        print("Aucun étudiant trouvé.")
        return
    print("Étudiants disponibles :")
    for student in students:
        print(f" - {student['firstName']} {student['lastName']} (ID : {student['id']})")

    # Étape 3 : L'administrateur sélectionne un examen (simulation avec l'ID 1)
    exam_id = 1
    exam = get_exam_by_id(exam_id)
    if exam is None:
        return

    # Étape 4 : L'administrateur sélectionne les étudiants concernés
    # Pour cette simulation, on attribue tous les étudiants trouvés à l'examen.
    selected_student_ids = [student["id"] for student in students]

    # Attribution des étudiants à l'examen
    updated_exam = assign_students_to_exam(exam_id, selected_student_ids)
    if updated_exam:
        print("Examen mis à jour :")
        print(" - Titre :", updated_exam.get("examTitle"))
        print(" - Étudiants associés :", [stu["id"] for stu in updated_exam.get("students", [])])

    # Étape 5 : Simulation de la consultation des examens par un étudiant
    # On récupère un étudiant (le premier de la liste) et affichons sa liste d'examens.
    student_id = selected_student_ids[0]
    print(f"\nL'étudiant avec l'ID {student_id} consulte ses examens (simulation) :")
    url = f"{BASE_URL}/users/{student_id}"
    response = requests.get(url)
    if response.status_code == 200:
        user = response.json()
        exams = user.get("exams", [])
        print("Liste des examens de l'étudiant :")
        for ex in exams:
            print(f" - {ex['examTitle']} (ID : {ex['id']})")
    else:
        print("Erreur lors de la récupération des informations de l'étudiant :", response.text)

if __name__ == "__main__":
    main()