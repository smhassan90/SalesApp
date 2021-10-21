package com.greenstar.controller.seportal;

import com.google.gson.Gson;
import com.greenstar.dal.Products;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 21st October, 2021
 */

@Controller
public class Product {
    @RequestMapping(value = "/productListing", method = RequestMethod.GET)
    @ResponseBody
    public String salesUpdate(){
        List<Products> products = new ArrayList<>();
        Products product = new Products();
        product.setName("Do");
        product.setImageURL("https://www.greenstar.org.pk/assets/images/products/do.png");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country’s first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");

        products.add(product);

        product = new Products();
        product.setName("Touch");
        product.setDescription("In 1996, Greenstar launched Touch – an upscale brand meant to target the Pakistani urban customer");
        product.setImageURL("https://www.greenstar.org.pk/assets/images/products/touch2e.png");

        products.add(product);

        product = new Products();
        product.setName("Sathi");
        product.setDescription("With a market share of over 70%* and being the number 1 Pakistani condom brand, Sathi is a feather in the cap of Greenstar’s FMCG range.");
        product.setImageURL("https://www.greenstar.org.pk/assets/images/products/touch3.png");

        products.add(product);
        return new Gson().toJson(products);
    }
}

