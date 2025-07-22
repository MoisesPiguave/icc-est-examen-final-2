package models;

import java.util.*;

    public class Maquina {
    String nombre;
    String ip;
    List<Integer> codigos;
    int subred;
    long riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
        calcularSubred();
        calcularRiesgo();
    }

    private void calcularSubred() {
        String[] octeto = ip.split("\\.");
        if (octeto.length >= 3) {
            this.subred = Integer.parseInt(octeto[2]);
        }
    }

    private void calcularRiesgo() {
        long sumaDeDivisiblesPor5 = 0;
        for (int codigo : codigos) {
            if (codigo % 5 == 0) {
                sumaDeDivisiblesPor5 += codigo;
            }
        }

        Set<Character> caracterUnico = new HashSet<>();
        for (char c : nombre.toCharArray()) {
            if (c != ' ') {
             caracterUnico.add(c);
            }
        }
        this.riesgo = sumaDeDivisiblesPor5 * caracterUnico.size();
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        return subred;
    }

    public long getRiesgo() {
        return riesgo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maquina maquina = (Maquina) o;
        return subred == maquina.subred && Objects.equals(nombre, maquina.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, subred);
    }
}