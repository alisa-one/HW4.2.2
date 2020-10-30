package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 15800;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {270, 260, 250, 800, 1000, 80, 150, 1600};
    public static int[] heroesDamages = {20, 25, 15, 0, 5, 8, 10, 20};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Doktor", "Golem", "Lucky", "Berserk", "Thor"};
    public static int DoktorHelp = 50;
    public static int roundCounter = 0;


    public static void main(String[] args) {
        // write your code here

        while (!isGameFinished()) {
            printStatistics();
            round();
        }
    }

    private static void printStatistics() {
        System.out.println("ROUND ----- " + roundCounter);
        System.out.println("______________");

        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i]);

        }
        System.out.println("______________");
    }

    public static void round() {
        roundCounter++;
        changeBossDefence();
        bossAngryState();
        heroesHit();
        ThorAbility();
        bossHits();
        doktorStep();
    }

    private static void ThorAbility() {
        Random ra = new Random();
        int randomM = ra.nextInt(3);
        if (randomM == 1) {
            System.out.println("Thor stunned the Boss");
            System.out.println("_____________________");
            bossDamage = 0;
        } else {
            bossDamage = 50;
        }


    }

    private static void heroesHit() {
        for (int i = 0; i < heroesDamages[5]; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random r = new Random();
                    int coeff = r.nextInt(7) + 2; // 2,3,4,5,6,7,8
                    if (bossHealth - heroesDamages[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critical damage " + heroesDamages[i] * coeff);
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
        }
    }

    private static void bossAngryState() {

        if (bossHealth <= 200) {
            Random r = new Random();
            int healthRand = r.nextInt(31) + 20;
            int damageRand = r.nextInt(11) + 10;
            bossHealth = bossDamage + healthRand;
            bossDamage = bossDamage + damageRand;
            System.out.println("Boss became angry: " +
                    "increased health by " + healthRand
                    + " and damage " + damageRand);
        }
    }

    private static void changeBossDefence() {

        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2,3,4,5,6
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void doktorStep() {
        Random P = new Random();
        int choiceOfHeroes = P.nextInt(7);

        if (heroesHealth[choiceOfHeroes] < 100 && heroesHealth[3] > 0 && DoktorHelp > 0 && heroesHealth[choiceOfHeroes] > 0) {
            Random h = new Random();
            int stepHelpRand = h.nextInt(5 + 2);
            heroesHealth[3] = heroesHealth[3] - DoktorHelp;
            int helpRand = DoktorHelp * stepHelpRand;
            if (helpRand > 0) {
                heroesHealth[choiceOfHeroes] = heroesHealth[choiceOfHeroes] + helpRand;

                System.out.println("Doctor help to" + " " + heroesAttackType[choiceOfHeroes] + " " + helpRand);
            }
        }
    }

    public static void bossHits() {
        {
            int stronghealth = heroesHealth[4];
            Random CH = new Random();
            boolean Chance = CH.nextBoolean();
            {
                {
                    for (int i = 0; i < heroesHealth.length; i++) {
                        if (heroesHealth[i] > 0 && bossHealth > 0) if (heroesHealth[3] > 0) {
                            if (heroesHealth[i] - bossDamage < 0) {
                                heroesHealth[i] = 0;
                            } else {
                                heroesHealth[i] = heroesHealth[i] - (bossDamage / 5) * 4;
                                if (heroesHealth[5] - (bossDamage / 5) * i>0) {
                                    heroesHealth[5] = heroesHealth[5] - (bossDamage / 5) * i;
                                }else{  heroesHealth[5]=0; }
                                if (Chance = true) {
                                    heroesHealth[4] = stronghealth;
                                }
                            }
                        }
                    }
                    Random F = new Random();
                    int Fight = F.nextInt(5) + 1;
                    if (bossDamage != 0) {
                        int backFight = bossDamage / Fight;
                        heroesHealth[6] = heroesHealth[6] + backFight;
                        heroesDamages[6] = heroesDamages[6] + backFight;
                    }
                }

            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }

        return allHeroesDead;
    }
}























