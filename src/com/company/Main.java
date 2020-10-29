package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {270, 260, 250};
    public static int[] heroesDamages = {20, 25, 15};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic"};
    public static int Doktor = 800;
    public static int DoktorHelp = 50;
    public static int roundCounter = 0;



    public static void main(String[] args) {
        // write your code here

        printStatistics();
        while (!isGameFinished()) {
            round();
        }


    }


    private static void round() {
        roundCounter++;
        changeBossDefence();
        bossAngryState();
        bossHits();
        heroesHit();
        doktorStep();
        printStatistics();

    }

    private static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
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

    private static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }
        int Doctor=Doktor -bossDamage;
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


    private static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }


    private static void doktorStep() {
        Random P = new Random();
        int choiceOfHeroes = P.nextInt(3);

        if (heroesHealth[choiceOfHeroes] > 100 && Doktor > 0 && DoktorHelp >0) {
            Random h = new Random();
            int stepHelpRand = h.nextInt(5 + 1);
            Doktor = Doktor - DoktorHelp;
            int helpRand = DoktorHelp * stepHelpRand;
            heroesHealth[choiceOfHeroes] = heroesHealth[choiceOfHeroes] + helpRand;
            String NumberOfHero= heroesAttackType[choiceOfHeroes];
            System.out.println("Doctor help to" +" " +heroesAttackType[choiceOfHeroes]+" "+ helpRand);
        }
    }

    private static void printStatistics() {
        System.out.println("ROUND ----- " + roundCounter);
        System.out.println("______________");
        Random h=new Random();
        int stepHelpRand=h.nextInt(5 + 1);;
        int helpRand=DoktorHelp * stepHelpRand;

        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i]);
            ;
        }
        System.out.println("______________");


            {

            }
        }
    }







