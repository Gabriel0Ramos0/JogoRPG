package rpg;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int level;
    private int experience;
    private int coins;
    private int tempDefense;
    private List<Item> inventory;
    private static final int BASE_XP_PER_LEVEL = 10;
    private static final int XP_INCREASE_PER_LEVEL = 10;
    private boolean passouPorParteDaHistoria = false;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.maxHealth = 100;
        this.attack = 5;
        this.defense = 10;
        this.tempDefense = defense;
        this.level = 1;
        this.experience = 0;
        this.inventory = new ArrayList<>();
        this.coins = 25;
    }
    
    private static int calculateXPPerLevel(int level) {
        return BASE_XP_PER_LEVEL + (level - 1) * XP_INCREASE_PER_LEVEL;
    }
    
    public void gainExperience(int amount) {
        experience += amount;
        JOptionPane.showMessageDialog(null, "Você ganhou " + amount + " de XP!");
        int xpRequiredForNextLevel = calculateXPPerLevel(level);
        if (experience >= xpRequiredForNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        experience -= calculateXPPerLevel(level - 1);
        maxHealth += 10;
        health = maxHealth;
        attack += 2;
        defense = (tempDefense + 2);
        JOptionPane.showMessageDialog(null, "Você subiu para o nível " + level + "!");
        JOptionPane.showMessageDialog(null, "Vida aumentou para " + maxHealth + ", Ataque aumentou para " + attack + ", Defesa aumentou para " + defense + ".");
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
    
    public void regenerarEscudo() {
    	setDefense(tempDefense);
    }
    
    public boolean jaPassouPorParteDaHistoria() {
        return passouPorParteDaHistoria;
    }

    public void marcarPassagemPorParteDaHistoria() {
        passouPorParteDaHistoria = true;
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
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}else {
			setName(JOptionPane.showInputDialog("É nescessário informar o nome!"));
		}
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}
    
}
