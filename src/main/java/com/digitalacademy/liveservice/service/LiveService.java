package com.digitalacademy.liveservice.service;

import com.digitalacademy.liveservice.model.*;
import com.digitalacademy.liveservice.repositories.*;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class LiveService {
    private StockRepository stockRepository;
    private LiveRepository liveRepository;
    private LiveStockRepository liveStockRepository;
    private CustomerRepository customerRepository;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    @Autowired
    private TransactionRepository transactionRepository;



    @Autowired
    public LiveService(StockRepository stockRepository,LiveRepository liveRepository,LiveStockRepository liveStockRepository,CustomerRepository customerRepository,TransactionRepository transactionRepository){
        this.stockRepository = stockRepository;
        this.liveRepository = liveRepository;
        this.liveStockRepository = liveStockRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
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
        String deepLink = "com.easyPay://transactions?" + "liveId="+liveStock.getLiveId()+"&stockId="+stockRandom;
        Stock stock = new Stock();
        stock.setStockId(stockRandom);
        stock.setUserId(userId);
        stock.setStockName(liveStock.getStockName());
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

    public LiveStock getLiveStockByStockId(String userId, String liveId,String stockId){
        LiveStock liveStock = liveStockRepository.getLiveStock(userId,liveId,stockId);
        if(liveStock.getCloseDeal() == 0){
            return null;
        }
        return liveStock;
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

    public TransactionResponse getAllTransaction(String liveId) {
//        Pusher pusher = new Pusher("857143", "c6ac9d5d09a6c242dfb8", "4476c85dac300ca54799");
//        pusher.setCluster("ap1");
//        pusher.setEncrypted(true);

        TransactionResponse response = new TransactionResponse();
        List<Transaction> transactions = transactionRepository.getTransactionByLiveId(liveId);
        Integer totalPrice = transactionRepository.getTotalPrice(liveId);
        response.setTransactionList(transactions);
        response.setTotalPrice(totalPrice);
//        pusher.trigger("my-channel", "my-event",response);
        return response;
    }


    public DeeplinkDataResponse getDeeplinkData(String customerId,int stockId) {
        DeeplinkDataResponse deeplinkDataResponse = new DeeplinkDataResponse();
        Stock stock = stockRepository.findByStockId(stockId);
        Customer customer = customerRepository.findByCustomerId(customerId);
        deeplinkDataResponse.setStock(stock);
        deeplinkDataResponse.setCustomer(customer);
        return deeplinkDataResponse;
    }


    public Transaction saveTransaction(Transaction body) {
        Pusher pusher = new Pusher("857143", "c6ac9d5d09a6c242dfb8", "4476c85dac300ca54799");
        pusher.setCluster("ap1");
        pusher.setEncrypted(true);
        int qtyProd = body.getQtyProd();
        Stock stock = stockRepository.findByStockId(body.getStockId());
        stock.setInStock(stock.getInStock()-qtyProd);
        Customer customer = customerRepository.findByCustomerId(body.getCustomerId());
        stockRepository.save(stock);
        LiveStock liveStock = liveStockRepository.findByLiveId(body.getLiveId());
        liveStock.setInStock(liveStock.getInStock()-qtyProd);
        liveStockRepository.save(liveStock);
        body.setStockName(stock.getStockName());
        body.setFirstName(customer.getFirstName());
        body.setLastName(customer.getLastName());
        body.setAddress(customer.getAddress());
        LocalDate date = LocalDate.now();
        String referenceCode = "";
        String str = date.toString();
        String [] arrOfStr = str.split("-");
        for (String a: arrOfStr){
            referenceCode = referenceCode + a;
        }
        String random = randomAlphaNumeric(18);
        referenceCode = referenceCode + random;
        body.setReferenceCode(referenceCode);
        transactionRepository.save(body);
        this.getAllTransaction(body.getLiveId());
        pusher.trigger(body.getLiveId(), "newTransaction",body);
        return body;
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }


}
