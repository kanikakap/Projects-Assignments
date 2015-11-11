__author__ = 'kanikakapoor'

import random

def diceRoll():
    rollSum = (random.randrange(6) + 1) + (random.randrange(6) + 1)
    #print('You Rolled a', rollSum)
    return rollSum

def evenWager():
    #print("This is Strategy - 1 Even Wager")

    moneyToPlay = 1000
    betWager = 100
    max = 10
    noOfGamesPlayed = 0


    # while player has money and chances to Play
    while moneyToPlay > 0 and max > 0:
        max = max - 1

        #print("------------------------------")
        noOfGamesPlayed = noOfGamesPlayed + 1
        #print('Chance Number: ', noOfGamesPlayed)

        # roll the dice
        firstRoll = diceRoll()

        if firstRoll == 7 or firstRoll == 11:
            #print("You WON!")
            moneyToPlay = moneyToPlay + betWager

        elif firstRoll == 2 or firstRoll == 3 or firstRoll == 12:
            #print("You LOST!")
            moneyToPlay = moneyToPlay - betWager

        else:
            point = firstRoll
            #print('Point is: ', point)
            #print("We are going to try to roll the point")
            newRoll = diceRoll()

            while newRoll != point and newRoll != 7:
                newRoll = diceRoll()

            if newRoll == point:
                #print("You scored the Point")
                #print("You WON!")
                moneyToPlay = moneyToPlay + betWager

            else:
                #print("You scored a 7")
                #print("You LOST!")
                moneyToPlay = moneyToPlay - betWager
    '''
    print("Strategy 1")
    print("Number of Games Played", noOfGamesPlayed)
    print("Ending Balance", moneyToPlay)
    '''
    print("Strategy 1       %s      %s" % (noOfGamesPlayed, moneyToPlay))



def martingale():

    #print("This is Strategy - 2 Martingale")


    moneyToPlay = 1000
    betWager = 100
    max = 10
    noOfGamesPlayed = 0


    # while player has money and chances to play
    while moneyToPlay > 0 and max > 0:
        max = max - 1

        #print("-------------------------------")
        noOfGamesPlayed = noOfGamesPlayed + 1
        #print('Chance Number: ', noOfGamesPlayed)

        # roll the dice
        firstRoll = diceRoll()

        if firstRoll == 7 or firstRoll == 11:
            #print("You WON!")
            moneyToPlay = moneyToPlay + betWager
            betWager = 100; #betWager remains 100


        elif firstRoll == 2 or firstRoll == 3 or firstRoll == 12:
            #print("You LOST!")
            moneyToPlay = moneyToPlay - betWager

            if betWager * 2 <= moneyToPlay:
                betWager = betWager * 2 #betWager is doubled if condition is satisfied
            else:
                betWager = moneyToPlay #if condition not satisfied, keep wager equal to money


        else:
            point = firstRoll
            #print('Point is: ', point)
            #print("We are going to try to roll the point")
            newRoll = diceRoll()

            while newRoll != point and newRoll != 7:
                newRoll = diceRoll()

            if newRoll == point:
                #print("You scored the Point")
                #print("You WON!")
                moneyToPlay = moneyToPlay + betWager
                betWager = 100

            else:
                #print("You scored a 7")
                #print("You LOST!")
                moneyToPlay = moneyToPlay - betWager
                if betWager * 2 <= moneyToPlay:
                    betWager = betWager * 2
                else:
                    betWager = moneyToPlay

    '''
    print("Strategy 2")
    print("Number of Games Played", noOfGamesPlayed)
    print("Ending Balance", moneyToPlay)
    print("Ending Wager", betWager)
    '''
    print("Strategy 2       %s      %s" % (noOfGamesPlayed, moneyToPlay))



def reverseMartingale():
    #print("This is Strategy - 3  Reverse Martingale")


    moneyToPlay = 1000
    betWager = 100
    max = 10
    noOfGamesPlayed = 0


    # while player has money and chances to play
    while moneyToPlay > 0 and max > 0:
        max = max - 1

        #print("-------------------------------")
        noOfGamesPlayed = noOfGamesPlayed + 1
        #print('Chance Number: ', noOfGamesPlayed)

        # roll the dice
        firstRoll = diceRoll()

        if firstRoll == 7 or firstRoll == 11:
            #print("You WON!")
            amount = moneyToPlay + betWager
            if  betWager * 2 <= moneyToPlay:
                betWager = betWager * 2
            else:
                betWager = moneyToPlay

        elif firstRoll == 2 or firstRoll == 3 or firstRoll == 12:
            #print("You LOST!")
            moneyToPlay = moneyToPlay - betWager
            betWager = 100

        else:
            point = firstRoll
            #print('Point is: ', point)
            #print("We are going to try to roll the point")
            newRoll = diceRoll()

            while newRoll != point and newRoll != 7:
                newRoll = diceRoll()

            if newRoll == point:
                #print("You scored the Point")
                #print("You WON!")
                moneyToPlay = moneyToPlay + betWager
                if betWager * 2 <= moneyToPlay:
                    betWager = betWager * 2
                else:
                    betWager = moneyToPlay

            else:
                #print("You scored a 7")
                #print("You LOST!")
                moneyToPlay = moneyToPlay - betWager
                betWager = 100

    
    
    #print "%s   %s  %s" % (noOfGamesPlayed, moneyToPlay, betWager)
    #print("Number of Games Played", noOfGamesPlayed)
    #print("Ending Balance", moneyToPlay)
    #print("Ending Wager", betWager)
    print("Strategy 3       %s      %s" % (noOfGamesPlayed, moneyToPlay))


if __name__ == '__main__':
    for x in range(1,6):
        print("***************************")
        print("Round : ",x)
        print("***************************")
        evenWager()
        martingale()
        reverseMartingale()
