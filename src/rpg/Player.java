package rpg;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class Player {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private List<Item> inventory;
    private int coins;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.attack = 5;
        this.defense = 10;
        this.inventory = new ArrayList<>();
        this.coins = 25;
    }
    
    public void decrementCoins(int amount) {
        if (amount >= 0 && coins >= amount) {
            coins -= amount;
        } else {
            System.out.println("Quantidade inválida de moedas ou saldo insuficiente.");
        }
    }
    
    public void incrementCoins(int amount) {
        if (amount >= 0) {
            coins += amount;
        } else {
            System.out.println("Quantidade inválida de moedas.");
        }
    }
    
    public void curaAnelRegenerativo() {
        for (Item item : inventory) {
            if (item.getName().equals("anelRegenerativo") && health < 100) {
                heal(1);
                JOptionPane.showMessageDialog(null, "O Anel Regenerativo curou 1 de vida!");
            }
        }
    }
    
    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getCoins() {
        return coins;
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        health = Math.max(0, health - actualDamage);
    }

    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }

    public void equipItem(Item item) {
        // Implemente a lógica para equipar o item, se necessário
    }

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}
    
}
