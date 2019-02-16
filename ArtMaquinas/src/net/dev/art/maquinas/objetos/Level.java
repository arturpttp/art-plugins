package net.dev.art.maquinas.objetos;

public enum Level {

	N1(1, 7L), N2(2, 6L), N3(4, 5L), N4(6, 4L), N5(10, 2L);

	private int drops;
	private long delay;

	private Level(int drops, long delay) {
		this.drops = drops;
		this.delay = delay;
	}

	public long getDelay() {
		return delay;
	}

	public int getDrops() {
		return drops;
	}
}
