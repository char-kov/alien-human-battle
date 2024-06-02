package battle;

import battle.character.Team;
import battle.character.alien.Mage;
import battle.character.alien.MindWarper;
import battle.character.alien.Warrior;
import battle.character.human.HumanTeam;
import battle.character.human.Medic;
import battle.character.Combatant;
import battle.character.Damageable;
import battle.character.Disableable;
import battle.character.alien.AlienTeam;
import battle.character.human.Soldier;
import battle.character.human.Spy;
import battle.character.type.Fighter;
import battle.character.type.Healer;
import battle.character.type.SpecialFighter;

import java.util.Scanner;

public class Battle {
	private static final Scanner INPUT = new Scanner(System.in);
	
	public static void main(String[] args) {
		Team humans = createHumanTeam();
		Team aliens = createAlienTeam();
		
		Team currentTeam;
		if(RandomUtil.random(2) == 1) {
			currentTeam = humans;
		} else {
			currentTeam = aliens;
		}
		
		System.out.println(currentTeam + " won the coin toss. They start!");
		System.out.println();
		while(!humans.hasLost() && !aliens.hasLost()) {			
			if(currentTeam == humans) {
				takeTurn(humans, aliens);
				currentTeam = aliens;
			} else {
				takeTurn(aliens, humans);
				currentTeam = humans;
			}
		}
		
		String winners;
		if(aliens.hasLost()) {
			winners = humans.getTeamName();
		} else {
			winners = aliens.getTeamName();
		}
		System.out.println(winners + " are victorious!");
	}
	
	private static HumanTeam createHumanTeam() {
		return new HumanTeam(
				"Earth Defenders",
				new Soldier(100, 30, "Bali"),
				new Soldier(125, 40, "Tokyo"),
				new Spy(150, 75, "Chicago"),
				new Medic(35, "Accident")
		);
	}
	
	private static AlienTeam createAlienTeam() {
		return new AlienTeam(
				"Mon-stars",
				new Warrior(100, 30, "Pluto"),
				new Warrior(125, 40, "Corneria"),
				new MindWarper(150, 60, "Hocotate"),
				new Mage(35, "Ragnarok")
		);
	}
	
	private static void takeTurn(Team myTeam, Team otherTeam) {
		boolean canTakeTurn = false;
		for(int i = 0; i < Team.TEAM_SIZE; i++) {
			canTakeTurn |= myTeam.getCombatant(i).canTakeTurn();
		}
		
		if(canTakeTurn) {
			Combatant combatant = chooseCombatant(myTeam);
			System.out.println();
	
			handleCombatOptions(combatant, myTeam, otherTeam);
			handleDisabledTurns(combatant, myTeam);
		} else {
			System.out.println(myTeam + ", everyone is dead or disabled, skipping turn");
			System.out.println();
			handleDisabledTurns(null, myTeam);
		}
	}
	
	private static Combatant chooseCombatant(Team myTeam) {
		printCombatantChoices(myTeam);
		int combatantChoice = nextIntClearNewLine();
		Combatant choice = myTeam.getCombatant(combatantChoice);
		while(choice == null || !choice.canTakeTurn()) {
			printPickValidOption();
			combatantChoice = nextIntClearNewLine();
			choice = myTeam.getCombatant(combatantChoice);
		}
		return choice;
	}
	
	private static void handleCombatOptions(Combatant combatant, Team myTeam, Team otherTeam) {
		//special fighter must come first because it is a subclass of fighter
		if(combatant instanceof SpecialFighter) {
			handleCombatOptions((SpecialFighter) combatant, otherTeam);
		} else if(combatant instanceof Fighter) {
			handleCombatOptions((Fighter) combatant, otherTeam);
		} else if(combatant instanceof Healer) {
			handleCombatOptions((Healer) combatant, myTeam);
		} else {
			throw new IllegalArgumentException(combatant.getClass().getSimpleName() + " is not handled");
		}
		System.out.println();

		
	}
	
	private static void handleCombatOptions(SpecialFighter specialFighter, Team otherTeam) {
		int attackIdx = 0;
		int infiltrateIdx = 1;
		String attackText = "attack";
		String infiltrateText = "infiltrate";
		
		printChooseAction();
		printChoiceOption(attackIdx, attackText);
		printChoiceOption(infiltrateIdx, infiltrateText);
		
		int healerChoice = nextIntClearNewLine();
		while(healerChoice != attackIdx && healerChoice != infiltrateIdx) {
			printPickValidOption();
			healerChoice = nextIntClearNewLine();
		}
		System.out.println();

		String actionText;
		if(healerChoice == attackIdx) {
			actionText = attackText;
		} else if(healerChoice == infiltrateIdx) {
			actionText = infiltrateText;
		} else {
			throw new IllegalStateException("invalid choice somehow made it through");
		}
		
		Damageable victim = chooseAliveDamageableCombatant(actionText, otherTeam);
		
		int damage;
		if(healerChoice == attackIdx) {
			damage = specialFighter.attack(victim);
		} else if(healerChoice == infiltrateIdx) {
			damage = specialFighter.infiltrate(victim);
		} else {
			throw new IllegalStateException("invalid choice somehow made it through");
		}
		
		System.out.println("Attacked for " + (-damage) + " damage");
	}
	
	private static void handleCombatOptions(Fighter fighter, Team otherTeam) {		
		Damageable victim = chooseAliveDamageableCombatant("attack", otherTeam);
		int damage = fighter.attack(victim);
		System.out.println("Attacked for " + (-damage) + " damage");
	}
	
	private static Damageable chooseAliveDamageableCombatant(String action, Team team) {
		printActionRecipientChoices(action, team);
		int combatantChoice = nextIntClearNewLine();
		Combatant combatant = team.getCombatant(combatantChoice);
		while(combatant == null
				|| !isAliveDamageable(combatant)) {
			printPickValidOption();
			combatantChoice = nextIntClearNewLine();
			combatant = team.getCombatant(combatantChoice);
		}
		return (Damageable) combatant;
	}
	
	private static void handleCombatOptions(Healer healer, Team myTeam) {
		int healIdx = 0;
		int sacrificeIdx = 1;
		String healText = "heal";
		String sacrificeText = "sacrifice";
		
		printChooseAction();
		printChoiceOption(healIdx, healText);
		printChoiceOption(sacrificeIdx, sacrificeText);
		
		int healerChoice = nextIntClearNewLine();
		while(healerChoice != healIdx && healerChoice != sacrificeIdx) {
			printPickValidOption();
			healerChoice = nextIntClearNewLine();
		}
		System.out.println();

		String actionText;
		if(healerChoice == healIdx) {
			actionText = healText;
		} else if(healerChoice == sacrificeIdx) {
			actionText = sacrificeText + " for";
		} else {
			throw new IllegalStateException("invalid choice somehow made it through");
		}
		
		Damageable recipient = chooseAliveDamageableCombatant(actionText, myTeam);
		
		int healAmount;
		if(healerChoice == healIdx) {
			healAmount = healer.heal(recipient);
		} else if(healerChoice == sacrificeIdx) {
			healAmount = healer.sacrifice(recipient);
		} else {
			throw new IllegalStateException("invalid choice somehow made it through");
		}
		
		System.out.println("Healed for " + healAmount + " HP");
	}
	
	private static int nextIntClearNewLine() {
		int result = INPUT.nextInt();
		INPUT.nextLine(); //clear new line character
		return result;
	}
	
	private static void printCombatantChoices(Team myTeam) {
		System.out.println(myTeam + ", pick someone to take a turn: ");
		for(int i = 0; i < Team.TEAM_SIZE; i++) {
			Combatant combatant = myTeam.getCombatant(i);
			String option = combatant.canTakeTurn() ? Integer.toString(i) : "X";
			String combatantAndStatus = combatant.getClass().getSimpleName() + " (" + combatant.getStatus() + ")";
			printChoiceOption(option, combatantAndStatus);
		}
	}
	
	private static void printActionRecipientChoices(String action, Team recievingTeam) {
		System.out.println("Pick someone to " + action + ": ");
		for(int i = 0; i < Team.TEAM_SIZE; i++) {
			Combatant recipient = recievingTeam.getCombatant(i);
			String option = isAliveDamageable(recipient) ? Integer.toString(i) : "X";
			String combatantAndStatus = recipient.getClass().getSimpleName() + " (" + recipient.getStatus() + ")";
			printChoiceOption(option, combatantAndStatus);
		}
	}
	
	private static boolean isAliveDamageable(Combatant combatant) {
		return combatant instanceof Damageable
				&& ((Damageable)combatant).isAlive();
	}
	
	private static void printChoiceOption(int optionIdx, String optionText) {
		printChoiceOption(Integer.toString(optionIdx), optionText);
	}
	
	private static void printChoiceOption(String optionIdx, String optionText) {
		System.out.println(optionIdx + ") " + optionText);
	}
	
	private static void printPickValidOption() {
		System.out.println("Please pick a valid option.");
	}
	
	private static void printChooseAction() {
		System.out.println("Choose an action:");
	}
	
	private static void handleDisabledTurns(Combatant personThatTookAction, Team team) {
		for(int i = 0; i < Team.TEAM_SIZE; i++) {
			Combatant combatant = team.getCombatant(i);
			if(combatant instanceof Disableable
					&& combatant != personThatTookAction) {
				((Disableable) combatant).skipTurn();
			}
		}
	}
}
