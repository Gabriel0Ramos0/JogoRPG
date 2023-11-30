package rpg;

public class Monstro {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int xp;

    public Monstro(String nome, int vida, int ataque, int defesa, int xp) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.xp = xp;
    }
    
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defesa);
        vida = Math.max(0, vida - actualDamage);
    }
    
    //Comum
    public static Monstro besta() {
        return new Monstro("Besta", 20, 5, 5, 10);
    }
    
    //Comum
    public static Monstro Lobo() {
        return new Monstro("Lobo Feroz", 21, 8, 2, 15);
    }
    
	// Comum
    public static Monstro Cão() {
        return new Monstro("Cão Infernal", 25, 7, 5, 15);
    }

    // Comum
    public static Monstro Goblin() {
        return new Monstro("Goblin", 18, 6, 3, 12);
    }

    // Epic
    public static Monstro DragaoDeGelo() {
        return new Monstro("Dragão de Gelo", 40, 5, 15, 60);
    }

    //Epic
    public static Monstro Esqueleto() {
        return new Monstro("Esqueleto Sombrio", 35, 6, 20, 25);
    }

    //Epic
    public static Monstro Ogro() {
        return new Monstro("Ogro Gigante", 30, 10, 5, 20);
    }

    // Boss
    public static Monstro Dragao() {
        return new Monstro("Dragão Negro", 75, 15, 40, 100);
    }
    
    // Boss
    public static Monstro Mercador() {
        return new Monstro("Mercador Sombrio", 40, 12, 8, 40);
    }

    public int getXP() {
        return xp;
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
