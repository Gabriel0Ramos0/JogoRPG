package rpg;

public class Consumivel extends Item {
    private int regeneracaoVida;

    public Consumivel(String name, int value, int quantity, int regeneracaoVida) {
        super(name, value, quantity, "Consumível");
        this.regeneracaoVida = regeneracaoVida;
    }

    public int getRegeneracaoVida() {
        return regeneracaoVida;
    }
    
    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }
}
