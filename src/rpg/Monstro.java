package rpg;

public class Monstro {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int xp;
    private int ouro;

    public Monstro(String nome, int vida, int ataque, int defesa, int xp, int ouro) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.xp = xp;
        this.ouro = ouro;
    }
    
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defesa);
        vida = Math.max(0, vida - actualDamage);
    }
    
    //Comum
    public static Monstro besta() {
        return new Monstro("Besta", 12, 5, 5, 5, 7);
    }
    
    //Comum
    public static Monstro Lobo() {
        return new Monstro("Lobo Feroz", 21, 8, 2, 10, 7);
    }
    
	// Comum
    public static Monstro Cão() {
        return new Monstro("Cão Infernal", 25, 7, 5, 15, 7);
    }

    // Comum
    public static Monstro Goblin() {
        return new Monstro("Goblin", 18, 6, 3, 12, 7);
    }

    // Epic
    public static Monstro Zumbi() {
        return new Monstro("Zumbi Sombrio", 40, 5, 15, 35, 15);
    }

    //Epic
    public static Monstro Esqueleto() {
        return new Monstro("Esqueleto Sombrio", 35, 6, 20, 25, 15);
    }

    //Epic
    public static Monstro Gigante() {
        return new Monstro("Gigante Sombrio", 30, 10, 5, 20, 15);
    }
    
    // Mini Boss
    public static Monstro Mercador() {
        return new Monstro("Mercador Sombrio", 40, 12, 8, 75, 35);
    }

    // Boss
    public static Monstro DragaoNegro() {
        return new Monstro("Dragão Negro", 100, 15, 40, 100, 50);
    }
    
    //Boss Final
    public static Monstro Sombras() {
    	return new Monstro("Sombra Devoradora", 174, 35, 52, 200, 65);
    }

    public int getXP() {
        return xp;
    }
    
    public int getOuro() {
    	return ouro;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}
