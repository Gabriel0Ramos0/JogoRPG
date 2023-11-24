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
