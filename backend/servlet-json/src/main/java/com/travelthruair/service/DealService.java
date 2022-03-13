package com.travelthruair.service;

import com.travelthruair.model.Deal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.gson.Gson;

public class DealService {
    private ConcurrentMap<Integer, Deal> deals;
    private AtomicInteger key;

    public DealService() {
        this.deals = new ConcurrentHashMap<Integer, Deal>();
        key = new AtomicInteger();

        this.addDeal(new Deal("Aa", 1000, "Mumbai", "Delhi"));
        this.addDeal(new Deal("Bb", 6000, "Amritsar", "Bangalore"));
        this.addDeal(new Deal("Cc",4000, "Kolkata", "Chennai"));
    }

    public String findAllDeals() {
        List<Deal> list = new ArrayList<Deal>(this.deals.values());
        // convert the list to json and return as string
        return this.toJson(list);
    }

    public boolean createDeal(String jsonPayload) {
        if (jsonPayload == null) return false;

        Gson gson = new Gson();
        try {
            Deal deal = (Deal) gson.fromJson(jsonPayload, Deal.class);
            if (deal != null) {
                return this.addDeal(deal);
            }
        }
        catch (Exception e) {}
        return false;
    }

    private String toJson(Object list) {
        if (list == null) return null;
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
    }

    private boolean addDeal(Deal deal) {
        Integer id = key.incrementAndGet();
        deal.setId(id);
        this.deals.put(id, deal);
        return true;
    }
}

