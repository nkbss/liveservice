package com.digitalacademy.liveservice.controller;

import com.digitalacademy.liveservice.constants.LiveResponse;
import com.digitalacademy.liveservice.model.*;
import com.digitalacademy.liveservice.service.LiveService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/live")
public class LiveController {
    private LiveService liveService;
    @Autowired
    public LiveController(LiveService liveService){
        this.liveService = liveService;
    }

    @PostMapping("/stock/create")
    public ResponseEntity<?> createStock(@Valid @RequestBody Stock body , @RequestParam String userId) throws JSONException {
        Stock stock = liveService.createStock(body,userId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.CREATED.getCode()+"",
                LiveResponse.CREATED.getMessage()),stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/customer/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer body,@RequestParam String customerId){
        Customer customer = liveService.createCustomer(body, customerId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.CREATED.getCode()+"",
                LiveResponse.CREATED.getMessage()),customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/customer/getCustomer")
    public ResponseEntity<?> getCustomer(@RequestParam String customerId){
        List <Customer> customers = liveService.getCustomer(customerId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.CREATED.getCode()+"",
                LiveResponse.CREATED.getMessage()),customers);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLive(@Valid @RequestBody Live body, @RequestParam String userId ) throws JSONException{
        Live live = liveService.createLive(body,userId);
//        JSONObject jsonObject = new JSONObject(body);
//        System.err.println(body);
//        System.err.println(jsonObject.get("lives"));

        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.CREATED.getCode()+"",
                LiveResponse.CREATED.getMessage()),live);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/stock/getAllStockByUserId")
    public ResponseEntity<?>  getAllStockByUserId(@RequestParam String userId){
        List<Stock> stocks = liveService.getAllStockByUserId(userId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()),stocks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stock/getStockByStockId")
    public ResponseEntity<?> getStockByStockId (@RequestParam String userId,@RequestParam int stockId){
        Stock stock = liveService.getStockByStockId(userId,stockId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()),stock);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/generateDeepLink")
    public ResponseEntity<?> generateDeepLink(@Valid @RequestBody LiveStock body, @RequestParam String userId) throws JSONException{
        LiveStock liveStock = liveService.generateDeepLink(body,userId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()),liveStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAllLiveStock")
    public ResponseEntity<?> getAllLiveStockByLiveId(@RequestParam String userId, @RequestParam String liveId){
        List<LiveStock> liveStocks = liveService.getAllLiveStockByLiveId(userId,liveId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()),liveStocks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLiveStock")
    public ResponseEntity<?> getLiveStockByStockId(@RequestParam String userId, @RequestParam String liveId, @RequestParam String stockId){
        LiveStock liveStock = liveService.getLiveStockByStockId(userId,liveId,stockId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()),liveStock);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/closeDeal")
    public ResponseEntity<?> closeDealByLiveId(@RequestParam String userId,@RequestParam String liveId){
        liveService.closeDeal(userId,liveId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getTransaction")
    public ResponseEntity<?> getTransaction(@RequestParam String liveId) {
        TransactionResponse transactions = liveService.getAllTransaction(liveId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()), transactions);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getDeeplinkData")
    public ResponseEntity<?> deeplinkData(@RequestParam String liveId) {
        DeeplinkDataResponse deeplinkDataResponse = liveService.getDeeplinkData(liveId);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()), deeplinkDataResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/easyPay")
    public ResponseEntity<?> easyPay(@RequestBody  Transaction body) {
        Transaction transaction = liveService.saveTransaction(body);
        ResponseModel response = new ResponseModel(new StatusModel(LiveResponse.SUCCESS.getCode()+"",
                LiveResponse.SUCCESS.getMessage()),transaction);
        return ResponseEntity.ok(response);
    }

}
