 package controllers;

import java.util.*;
import models.Maquina;


 public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> filteredStack = new Stack<>();
        for (Maquina maquina : maquinas) {
            if (maquina.getSubred() > umbral) {
                filteredStack.push(maquina);
            }
        }
        return filteredStack;
    }

    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        TreeSet<Maquina> sortedSet = new TreeSet<>(new Comparator<Maquina>() {
            @Override
            public int compare(Maquina m1, Maquina m2) {
                int subredCompare = Integer.compare(m2.getSubred(), m1.getSubred());
                if (subredCompare != 0) {
                    return subredCompare;
                }
                return m1.getNombre().compareTo(m2.getNombre());
            }
        });

        List<Maquina> temp = new ArrayList<>();
        while (!pila.isEmpty()) {
            temp.add(pila.pop());
        }

        for (Maquina maquina : temp) {
            sortedSet.add(maquina);
        }
        return sortedSet;
    }

    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        TreeMap<Integer, Queue<Maquina>> mapaAgrupado = new TreeMap<>();
        for (Maquina maquina : maquinas) {
            int riesgo = (int) maquina.getRiesgo();
            mapaAgrupado.computeIfAbsent(riesgo, k -> new LinkedList<>()).offer(maquina);
        }
        return mapaAgrupado;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        Stack<Maquina> resultStack = new Stack<>();
        int maxCount = -1;
        int maxRiesgo = -1;
        Queue<Maquina> grupo = null;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int currentRiesgo = entry.getKey();
            Queue<Maquina> grupoActual = entry.getValue();
            if (grupoActual.size() > maxCount) {
                maxCount = grupoActual.size();
                maxRiesgo = currentRiesgo;
                grupo = grupoActual;
            } else if (grupoActual.size() == maxCount) {
                if (currentRiesgo > maxRiesgo) {
                    maxRiesgo = currentRiesgo;
                    grupo = grupoActual;
                }
            }
        }
        return resultStack;
    }

   
}