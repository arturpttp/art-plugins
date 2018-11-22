package net.dev.art.rc.enums;

import net.dev.art.rc.enums.*;

public enum Commands {

	BAN("ban", Permissions.ADMIN), KICK("kick", Permissions.ADMIN);

	String command;
	Permissions permissions;

	private Commands(String comando, Permissions permissao) {
		this.command = comando;
		this.permissions = permissao;
	}

	public String getCommand() {
		return command;
	}
	
	public Permissions getPermissions() {
		return permissions;
	}
	
}
