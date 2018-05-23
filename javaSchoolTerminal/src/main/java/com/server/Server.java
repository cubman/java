package com.server;

import com.exceptions.CardWasNotFound;
import com.exceptions.InsuffienceFunds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private static final String FILENAME = "src/main/resources/data.txt";

    private static volatile Server server;
    Map<String, Data<String, String, String, BigDecimal>> data = new HashMap<>();

    private Server() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] datas = sCurrentLine.split(" ");
                data.put(datas[0], new Data<>(datas[1], datas[0], datas[2], new BigDecimal(Double.valueOf(datas[3]))));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        Server localInstance = server;
        if (localInstance == null) {
            synchronized (Server.class) {
                localInstance = server;
                if (localInstance == null) {
                    server = localInstance = new Server();
                }
            }
        }

        return localInstance;
    }

    public List<String> getAllCards() {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, Data<String, String, String, BigDecimal>> elemEntry : data.entrySet())
            list.add(elemEntry.getKey());

        return list;
    }

    public Data<String, String, String, BigDecimal> getDataByCard(String card) throws CardWasNotFound {
        if (containsCard(card))
            return data.get(card).clone();

        throw new CardWasNotFound();
    }

    private boolean containsCard(String card) {
        return data.containsKey(card);
    }

    public void putMoney(String card, BigDecimal money) throws CardWasNotFound {
        if (containsCard(card))
            data.get(card).setMoney(data.get(card).getMoney().add(money));
        else
            throw new CardWasNotFound();
    }

    public void withdrawMoney(String card, BigDecimal money) throws CardWasNotFound, InsuffienceFunds {
        if (containsCard(card)) {
            BigDecimal mon = data.get(card).getMoney();

            if (mon.compareTo(money) < 0)
                throw new InsuffienceFunds();

            data.get(card).setMoney(mon.add(BigDecimal.valueOf((-1)* money.doubleValue())));
        } else
            throw new CardWasNotFound();
    }
}
