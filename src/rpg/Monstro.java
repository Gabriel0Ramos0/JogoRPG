package rpg;

public class Monstro {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;

    public Monstro(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }
    
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defesa);
        vida = Math.max(0, vida - actualDamage);
    }
    
    public static Monstro besta() {
        return new Monstro("Besta", 20, 5, 5);
    }
    
    public static Monstro monstroLobo() {
        return new Monstro("Lobo Feroz", 20, 8, 2);
    }

    public static Monstro monstroEsqueleto() {
        return new Monstro("Esqueleto Assustador", 15, 6, 4);
    }

    public static Monstro monstroOgro() {
        return new Monstro("Ogro Gigante", 30, 10, 5);
    }

    // Boss
    public static Monstro monstroBoss() {
        return new Monstro("Dragão Negro (Boss)", 50, 15, 10);
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
