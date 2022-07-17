package com.test.luizalabs.service.impl;

import com.test.luizalabs.dto.OrderDTO;
import com.test.luizalabs.dto.ProductDTO;
import com.test.luizalabs.dto.UserDTO;
import com.test.luizalabs.exception.NormalizedException;
import com.test.luizalabs.service.NormalizedService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NormalizedServiceImpl implements NormalizedService {

    public List<UserDTO> normalizedAllLine(List<String> lines) throws NormalizedException {
        List<UserDTO> users = new ArrayList<>();

        lines.stream().forEach(l ->{

            try {
                UserDTO user = null;
                UserDTO newUser = this.newUser(l.trim());

                Optional<UserDTO> finalUser = users.stream()
                        .filter(u -> u.getIdUser().equals(newUser.getIdUser())).findFirst();

            if(finalUser.isPresent()){
                user = finalUser.get();
            }else{
                users.add(newUser);
                user = newUser;
            }

            this.fillOrder(user, l);

            } catch (Exception e) {
                throw new NormalizedException(e.getMessage());
            }

        });

        return users;
    }


    private void fillOrder(UserDTO user, String line) throws ParseException , IllegalArgumentException{
        List<OrderDTO> orders = user.getOrders();

        OrderDTO order = null;
        OrderDTO newOrder = this.newOrder(line);
        Optional<OrderDTO> finalOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(newOrder.getOrderId())).findFirst();

        if(finalOrder.isPresent()){
            order = finalOrder.get();
        }else{
            orders.add(newOrder);
            order = newOrder;
        }

        this.fillProduct(order,line);
    }

    private void fillProduct(OrderDTO order, String line) {
        ProductDTO newProduct = this.newProduct(line);
        order.setTotal(order.getTotal()+newProduct.getValue());
        order.getProducts().add(newProduct);
    }

    private String subStringLine(int start,int end, String line){
        return line.substring(start, end);
    }

    private UserDTO newUser(String line) throws NumberFormatException {
        return UserDTO.builder()
                .idUser(Long.parseLong(this.subStringLine(0,10, line)))
                .name(this.subStringLine(10,55, line).trim())
                .orders(new ArrayList<>()).build();
    }

    private OrderDTO newOrder(String line) throws
            IllegalArgumentException, ParseException {
        return OrderDTO.builder()
                .orderId(Long.parseLong(this.subStringLine(55,65, line)))
                .date(new SimpleDateFormat("yyyyMMdd").parse(this.subStringLine(87,95, line)))
                .products(new ArrayList<>()).build();
    }

    private ProductDTO newProduct(String line) throws
            NumberFormatException  {
        return ProductDTO.builder()
                .productId(Long.parseLong(this.subStringLine(65,75, line)))
                .value(Double.parseDouble(this.subStringLine(75,87, line))).build();

    }

}
