package net.dev.art.core.objects;

public abstract class API {

	boolean storable;

	public void setStorable(boolean storable) {
		this.storable = storable;
	}

	public boolean isStorable() {
		return storable;
	}

}
