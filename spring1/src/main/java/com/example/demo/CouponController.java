package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CouponController {

    private static List<CouponDTO> m_coupons = new ArrayList<>();

    // static ctor
    static {
        m_coupons.add( new CouponDTO(1, "Caffe") );
        m_coupons.add( new CouponDTO(2, "Movie VIP") );
        m_coupons.add( new CouponDTO(3, "Sky jump") );
    }

    @GetMapping("/coupon")
    public List<CouponDTO> getCoupons()
    {
        return m_coupons;
    }

    @GetMapping("/coupon/{id}")
    public CouponDTO doGetCouponById(@PathVariable("id") int id)
    {
        for(CouponDTO c : m_coupons)
        {
            if (c.getId() == id)
            {
                c.setTitle(c.getTitle());
                return c;
            }
        }
        return null;
    }

    @PostMapping("/coupon")
    public ResponseEntity<String> addCoupon(@RequestBody CouponDTO c)
    {
        m_coupons.add(c);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(
                "Custom header set", headers, HttpStatus.CREATED);
    }

    @PutMapping("/coupon/{id}")
    public void updateCoupon(@PathVariable("id") int id, @RequestBody CouponDTO update_c)
    {
        for(CouponDTO c : m_coupons)
        {
            if (c.getId() == id)
            {
                c.setId(update_c.getId());
                c.setTitle(update_c.getTitle());
                return;
            }
        }
    }

    @DeleteMapping("/coupon/{id}")
    public void delCouponById(@PathVariable("id") int id)
    {
        Iterator<CouponDTO> iter = m_coupons.iterator();
        while (iter.hasNext()) {
            CouponDTO c = iter.next();
            if (c.getId() == id) {
                iter.remove();
                return;
            }
        }
    }

}