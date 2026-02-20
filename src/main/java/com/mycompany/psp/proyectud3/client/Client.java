/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psp.proyectud3.client;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author jsbje
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private HashMap<String, List<String>> messages; 

    public Client(String name) {
        this.name = name;
        this.messages = new HashMap<>();
    }

    public void addMessage(String from, String message) {
        messages.computeIfAbsent(from, k -> new ArrayList<>()).add(message);
    }

    public List<String> getMessagesFrom(String from) {
        return messages.getOrDefault(from, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public HashMap<String, List<String>> getMessages() {
        return messages;
    }


}
