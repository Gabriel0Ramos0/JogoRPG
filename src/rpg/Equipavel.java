package rpg;

public class Equipavel extends Item {
	private int dano;
	private int def;
	private int vida;

	public Equipavel(String name, int value, int quantity, int dano, int def, int vida) {
		super(name, value, quantity, "Equipavel");
		this.dano = dano;
		this.def = def;
		this.vida = vida;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
}
