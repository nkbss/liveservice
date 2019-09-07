package com.digitalacademy.liveservice.service;

import com.digitalacademy.liveservice.model.*;
import com.digitalacademy.liveservice.repositories.*;
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
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;



    @Autowired
    public LiveService(StockRepository stockRepository,LiveRepository liveRepository,LiveStockRepository liveStockRepository,CustomerRepository customerRepository){
        this.stockRepository = stockRepository;
        this.liveRepository = liveRepository;
        this.liveStockRepository = liveStockRepository;
        this.customerRepository = customerRepository;
    }

    public Stock createStock(Stock body,String userId){
        body.setUserId(userId);
        stockRepository.save(body);
        return body;
    }

    public Customer createCustomer(Customer body,String customerId){
        body.setCustomerId(customerId);
        String bankAccount = "xxx-xxx" + new Random().nextInt(999) + "-" + new Random().nextInt(9);
        body.setBankAccount(bankAccount);
        customerRepository.save(body);
        return body;
    }

    public List<Customer> getCustomer(String customerId){
        List <Customer> customers = customerRepository.getCustomer(customerId);
        return customers;
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
        stock.setPrice(liveStock.getPrice());
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

    public TransactionResponse getAllTransaction() {
        TransactionResponse response = new TransactionResponse();
        List<Transaction> transaction = transactionRepository.findAll();
        Integer totalPrice = transactionRepository.getTotalPrice();
        response.setTransactionList(transaction);
        response.setTotalPrice(totalPrice);
        return response;
    }

    // after click on deeplink
    public CustomerPayResponse getDeeplinkData(String liveId, int stockId) {
        CustomerPayResponse customerPayResponse = new CustomerPayResponse();
        LiveStock liveStocks = liveStockRepository.findByLiveId(liveId);
        Stock stock = stockRepository.findByStockId(stockId);
        customerPayResponse.setLiveStock(liveStocks);
        customerPayResponse.setStock(stock);
        return customerPayResponse;
    }



}
