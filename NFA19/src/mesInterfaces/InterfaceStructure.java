package mesInterfaces;

//type de strcuture //type de clé (integer, String) = comme par exemplepour Article
public interface InterfaceStructure<TypeCode, TypeElement> {
	void ajouter(TypeElement elem);
	void supprimer(TypeCode code);
	void modifier(TypeCode code);
	int taille();
}
