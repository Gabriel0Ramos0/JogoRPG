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
    
    // Comum
    public static Monstro besta() {
        return new Monstro("Besta", 18, 7, 5, 12, 10);
    }

    // Comum
    public static Monstro Lobo() {
        return new Monstro("Lobo Feroz", 25, 10, 4, 15, 10);
    }

    // Comum
    public static Monstro Cão() {
        return new Monstro("Cão Infernal", 30, 9, 8, 20, 12);
    }

    // Comum
    public static Monstro Goblin() {
        return new Monstro("Goblin", 22, 8, 5, 18, 12);
    }

    // Epic
    public static Monstro Zumbi() {
        return new Monstro("Zumbi Sombrio", 50, 7, 20, 40, 25);
    }

    // Epic
    public static Monstro Esqueleto() {
        return new Monstro("Esqueleto Sombrio", 45, 9, 25, 30, 25);
    }

    // Epic
    public static Monstro Gigante() {
        return new Monstro("Gigante Sombrio", 40, 14, 8, 25, 25);
    }

    // Epic
    public static Monstro Guarda() {
        return new Monstro("Guarda", 50, 10, 10, 70, 35);
    }

    // Epic
    public static Monstro ZumbiR() {
        return new Monstro("Zumbi Retorcido", 65, 16, 25, 70, 35);
    }

    // Epic
    public static Monstro ZumbiA() {
        return new Monstro("Aberração Voadora", 40, 21, 15, 70, 35);
    }

    // Mini Boss
    public static Monstro Mercador() {
        return new Monstro("Mercador Sombrio", 55, 16, 10, 90, 45);
    }

    // Mini Boss
    public static Monstro SombraV() {
        return new Monstro("Sombra Voraz", 70, 16, 40, 90, 45);
    }

    // Mini Boss
    public static Monstro Guardião() {
        return new Monstro("Guardião Obsidiano", 90, 24, 45, 95, 45);
    }

    // Boss
    public static Monstro DragaoNegro() {
        return new Monstro("Dragão Negro", 120, 18, 45, 120, 60);
    }

    // Boss Final
    public static Monstro Sombras() {
        return new Monstro("Sombra Devoradora", 200, 40, 60, 250, 75);
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
