package fr.nelfdesign.meteonelf;

import java.io.Serializable;

    public class Ville implements Serializable{

        String name;

        public Ville(String name) {
            this.name = name;
        }

        public Ville(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ville{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
