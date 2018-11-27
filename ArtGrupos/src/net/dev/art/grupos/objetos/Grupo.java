package net.dev.art.grupos.objetos;

import java.util.List;

public class Grupo {

	String prefix, name;
	List<String> permissions;

	public Grupo(String prefix, String name, List<String> permissions) {
		this.prefix = prefix;
		this.name = name;
		this.permissions = permissions;
	}

	public String getPrefix() {
		return prefix;
	}

	public List<String> getPermissions() {
		return permissions;
	}
	
	public String getName() {
		return name;
	}

}
