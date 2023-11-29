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
    
    public static Monstro besta() {
        return new Monstro("Besta", 20, 5, 5, 10);
    }

    public static Monstro Lobo() {
        return new Monstro("Lobo Feroz", 21, 8, 2, 15);
    }

    public static Monstro Esqueleto() {
        return new Monstro("Esqueleto Assustador", 15, 6, 4, 15);
    }

    public static Monstro Ogro() {
        return new Monstro("Ogro Gigante", 30, 10, 5, 20);
    }

    // Boss
    public static Monstro Dragao() {
        return new Monstro("Dragão Negro (Boss)", 50, 15, 10, 50);
    }
    
 // Boss
    public static Monstro Mercador() {
        return new Monstro("Mercador Sombrio", 40, 12, 8, 35);
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
