package rpg;

import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private List<Item> inventory;
    private int gold;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.attack = 5;
        this.defense = 10;
        this.inventory = new ArrayList<>();
        this.gold = 0;
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

    public int getGold() {
        return gold;
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

    public void gainGold(int amount) {
        gold += amount;
    }
}
