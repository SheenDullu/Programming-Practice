package com.interview.code.day2;

import java.util.List;
import java.util.stream.Collectors;



/***
 *
 Stocks

 Portfolio: stocks, holdings-> transactions
 (Tesla) 100 stocks -> 1 hold trans BUY   P1   H1
 10 SELL trans
 (Nvidia) 50 stocks -> another holding    P2.  H3
 (Tesla) 25 stocks  P1.  H2


 Stocks
 Id, price


 Portfolio
 Id, stockId,



 Holdings
 Id, PId

 Transactions:
 Id, BUY/SELL, HoldingId, Quantity, price per unit,

 *
 * ***/
public class Solution {

    public void calculateProfit(List<PortFolio> portfolios) {
        double profit = 0.0; //100 120
        for(PortFolio p: portfolios) {
            List<Holdings> holdingsList = p.getHoldingsList();
            for(Holdings h: holdingsList){
                List<Transactions> transactions = h.getTransactions();
                List<Transactions> sold = transactions.stream().filter(t -> t.getTransactionType().equals(Type.SELL)).collect(Collectors.toList());
                List<Transactions> bought = transactions.stream().filter(t -> t.getTransactionType().equals(Type.BUY)).collect(Collectors.toList());

                bought.stream().map(v-> v.getPricePerUnit() * v.getQuantity());
            }
        }
    }


    public static void main(String[] args) {

        // cal current profits or loss



        // current evaluation of all stocks


    }
}


