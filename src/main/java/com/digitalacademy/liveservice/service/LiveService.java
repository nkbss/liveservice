package com.digitalacademy.liveservice.service;

import com.digitalacademy.liveservice.model.Live;
import com.digitalacademy.liveservice.model.LiveStock;
import com.digitalacademy.liveservice.model.Stock;
import com.digitalacademy.liveservice.repositories.LiveRepository;
import com.digitalacademy.liveservice.repositories.LiveStockRepository;
import com.digitalacademy.liveservice.repositories.StockRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LiveService {
    private StockRepository stockRepository;
    private LiveRepository liveRepository;
    private LiveStockRepository liveStockRepository;


    @Autowired
    public LiveService(StockRepository stockRepository,LiveRepository liveRepository,LiveStockRepository liveStockRepository){
        this.stockRepository = stockRepository;
        this.liveRepository = liveRepository;
        this.liveStockRepository = liveStockRepository;
    }

    public Stock createStock(Stock body,String userId){
        body.setUserId(userId);
        stockRepository.save(body);
        return body;
    }

    public List<Stock> getAllStockByUserId(String userId){
        List<Stock> stocks = stockRepository.findAllStockByUserId(userId);
        return stocks;
    }

    public Stock getStockByStockId(String userId,int stockId){
        Stock stock = stockRepository.getStockByStockId(userId,stockId);
        return stock;
    }

    public Live createLive(Live body, String userId){
        body.setUserId(userId);
        int random =  new Random().nextInt(9999)+10000;
        String liveRandom = "LIVE-" + random;
        body.setLiveId(liveRandom);
        liveRepository.save(body);
        return body;
    }

    public LiveStock generateDeepLink (LiveStock liveStock, String userId){
        int stockRandom = new Random().nextInt(9999)+10000;
        String deepLink = "SCB_LIVE/" + liveStock.getLiveId()+"/"+stockRandom;
        Stock stock = new Stock();
        stock.setStockId(stockRandom);
        stock.setUserId(userId);
        stock.setName(liveStock.getStockName());
        stock.setInStock(liveStock.getInStock());
        liveStock.setStockId(stockRandom);
        liveStock.setDeepLink(deepLink);
        liveStock.setCloseDeal(0);
        liveStock.setUserId(userId);
        stockRepository.save(stock);
        System.err.println(liveStock);
        liveStockRepository.save(liveStock);
        return liveStock;
    }

    public List<LiveStock> getAllLiveStockByLiveId(String userId,String liveId){
        List <LiveStock> liveStocks = liveStockRepository.findAllLiveStock(userId,liveId);
        return liveStocks;
    }

    public void closeDeal (String userId,String liveId){
        List<LiveStock> liveStocks = liveStockRepository.findAllLiveStock(userId,liveId);
        Live live = liveRepository.getLiveByLiveId(userId,liveId);
        live.setCloseDeal(1);
        liveRepository.save(live);
        System.err.println(live);
        for(int i =0;i<liveStocks.size();i++){
            LiveStock liveStockBody = liveStocks.get(i);
            liveStockBody.setCloseDeal(1);
            liveStockBody.setDeepLink("Expired");
            liveStockRepository.save(liveStockBody);
            System.err.println(liveStockBody);
        }

    }



}
