package net.dev.art.rc.enums;

import net.dev.art.rc.utils.Crypter;

public enum Users {

	arturpttp("arturpttp", "YXJ0dXJwdHRwCg==", Permissions.ADMIN), admin("admin", "YWRtaW4=", Permissions.ADMIN),
	moderador("moderador", "bW9kZXJhZG9y", Permissions.MODERADOR);

	String username, pass;
	Permissions permission;

	private Users(String username, String pass, Permissions permission) {
		this.username = username;
		this.pass = pass;
		this.permission = permission;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return Crypter.decode(pass);
	}

	public Permissions getPermission() {
		return permission;
	}

	public boolean hasPermission(Permissions perm) {
		return getPermission().equals(perm) ? true : false;
	}

}
