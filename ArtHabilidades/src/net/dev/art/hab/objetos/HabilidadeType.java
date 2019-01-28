package net.dev.art.hab.objetos;

public enum HabilidadeType {

	Minerar, Lenhador, Espada, Escavacao, Reparacao, Acrobacia, Herbalismo, Desarmado,
	Alquimia;

	public String getName() {
		switch (this) {
		case Acrobacia:
			return "Acrobacia";
		case Escavacao:
			return "Escavacao";
		case Espada:
			return "Espada";
		case Lenhador:
			return "Lenhador";
		case Minerar:
			return "Minerar";
		case Reparacao:
			return "Reparacao";
		case Herbalismo:
			return "Herbalismo";
		case Desarmado:
			return "Desarmado";
		case Alquimia:
			return "Alquimia";
		default:
			return "";
		}
	}
	
}
