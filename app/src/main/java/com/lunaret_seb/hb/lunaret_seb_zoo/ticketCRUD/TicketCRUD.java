package com.lunaret_seb.hb.lunaret_seb_zoo.ticketCRUD;

import com.lunaret_seb.hb.lunaret_seb_zoo.ticket.Ticket;

import java.util.ArrayList;

/**
 * Created by hb on 13/06/2016.
 */
public class TicketCRUD {
    static ArrayList<Ticket> ticketsList;

    public TicketCRUD() {
        ticketsList = new ArrayList<>();
        ticketsList.add(new Ticket("Tarif normal", 8));
        ticketsList.add(new Ticket("Tarif réduit", 6));
        ticketsList.add(new Ticket("Tarif enfant", 5));

        }

        public static void delete(Ticket ticket) {
            delete(ticketsList.indexOf(ticket));
        }

        private static void delete(int ticketId) {
            ticketsList.remove(ticketId);
        }

        public static void add(Ticket animal) {
            ticketsList.add(animal);
        }
        public static void update(Ticket ticket, Ticket newTicket) {
            int index = ticketsList.indexOf(ticket);
            Ticket currentTicket = ticketsList.get(index);
            currentTicket.setName(newTicket.getName());
            currentTicket.setPrice(newTicket.getPrice());
        }
        public static Ticket retrieve(String name) {
            for (Ticket ticket : ticketsList) {
                if (ticket.getName().equals(name))
                    return ticket;
            }
            return null;
        }

        public static ArrayList<Ticket> retrieveAll() {
            return ticketsList;
        }


    }