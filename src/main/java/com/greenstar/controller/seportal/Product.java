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

//1
        Products product = new Products();

        product.setName("Depo Queen");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_depo_queen.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//2
        product = new Products();
        product.setName("Depotone");
        product.setDescription("In 1996, Greenstar launched Touch – an upscale brand meant to target the Pakistani urban customer");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_depotone.jpg");
        products.add(product);
//3
        product = new Products();
        product.setName("Do");
        product.setDescription("With a market share of over 70%* and being the number 1 Pakistani condom brand, Sathi is a feather in the cap of Greenstar's FMCG range.");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_do.jpg");
        products.add(product);
//4
        product = new Products();
        product.setName("Do Ultra");
        product.setDescription("With a market share of over 70%* and being the number 1 Pakistani condom brand, Sathi is a feather in the cap of Greenstar's FMCG range.");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_do_ultra.jpg");
        products.add(product);
//5
        product = new Products();
        product.setName("ecp");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_ecp.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//6
        product = new Products();
        product.setName("Emergency Pills");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_emergency_pills.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//7
        product = new Products();
        product.setName("Enofer");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_enofer.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//8
        product = new Products();
        product.setName("Femi-Ject");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_femi_Ject.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//9
        product = new Products();
        product.setName("Feravi");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_feravi.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//10
        product = new Products();
        product.setName("Misotal");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_misotal.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//11
        product = new Products();
        product.setName("Nova-Ject");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_nova_ject.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//12
        product = new Products();
        product.setName("Novodol");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_novodol.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//13
        product = new Products();
        product.setName("Protect 5");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_protect_5.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//14
        product = new Products();
        product.setName("Safeload");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_safeload.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//15
        product = new Products();
        product.setName("Sathi");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_sathi.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//16
        product = new Products();
        product.setName("Sathi Plus");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_sathi_plus.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//17
        product = new Products();
        product.setName("Touch");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_touch.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//18
        product = new Products();
        product.setName("Vical");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_vical.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);
//19
        product = new Products();
        product.setName("Vitaferol");
        product.setImageURL("https://se.greenstar.org.pk/ProductImages/1_vitaferol.jpg");
        product.setDescription("In 2017, Greenstar made history in the segment by launching the country's first 3 in 1 brand containing ribbed, dotted and delay features within a single condom.");
        products.add(product);

        return new Gson().toJson(products);
    }
}