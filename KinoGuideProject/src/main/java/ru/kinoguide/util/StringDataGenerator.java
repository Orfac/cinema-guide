package ru.kinoguide.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StringDataGenerator {

    private List<List<String>> parts = new ArrayList<>();

    private int[][] iterationOrders;

    private int iterationIndex = 0;
    private boolean iterationOrdersCalculated = false;

    private String delimiter = " ";

    public StringDataGenerator() {
    }

    private void calculateIterationOrder() {
        int combinationsNum = parts.stream().mapToInt(List<String>::size).reduce(1, (a, b) -> a * b);
        iterationOrders = new int[combinationsNum][parts.size()];

        for (int partNumber = 0; partNumber < parts.size(); partNumber++) {
            for (int i = 0; i < combinationsNum; i++) {
                iterationOrders[i][partNumber] = i % parts.get(partNumber).size();
            }
        }
        shuffleArray(iterationOrders);
        iterationOrdersCalculated = false;
    }

    public void addPart(String... partValues) {
        if (partValues.length == 0) {
            throw new IllegalArgumentException("Part values should be present (at least 1)");
        }
        parts.add(Arrays.asList(partValues));
    }

    public String generateNextValue() {
        if (parts.size() == 0) {
            throw new IllegalStateException("At least 1 part should be added to generator before generation");
        }
        if (!iterationOrdersCalculated) {
            calculateIterationOrder();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterationOrders[iterationIndex].length; i++) {
            if (iterationOrders[iterationIndex][i] == -1) {
                // skipping, part is optional (not required) // TODO future feature
                continue;
            }
            sb.append(parts.get(i).get(iterationOrders[iterationIndex][i]));
            if (i != iterationOrders[iterationIndex].length - 1) {
                sb.append(" ");
            }
        }
        iterationIndex = (iterationIndex + 1) % iterationOrders.length;
        return sb.toString();
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public static void shuffleArray(int[][] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int[] a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    @Bean("filmNameGenerator")
    public static StringDataGenerator buildFilmNameGenerator() {
        StringDataGenerator stringDataGenerator = new StringDataGenerator();
        stringDataGenerator.addPart(
                "Нападение",
                "Возвращение",
                "Геноцид",
                "Воспоминания",
                "Потеря",
                "Убийство",
                "Возбуждение",
                "Воскрешение",
                "Служебный роман",
                "Кровавая резня",
                "Жестокая игра",
                "Техасская бойня",
                "Воскресный вечер",
                "Дождливое утро",
                "Вечер джаза и",
                "Настоящая любовь",
                "Всё о сексе");
        stringDataGenerator.addPart(
                "нацистов-серфингистов",
                "помидоров-убийц",
                "презерватива-убийцы",
                "41-летнего девственника",
                "Карла Маркса",
                "рождённых в СССР",
                "голливудских шлюх",
                "Елены Малышевой",
                "человека-личинки",
                "Клементе Кастелли",
                "Дона Корлеоне",
                "Тома Хагена",
                "Майкла Джексона",
                "Стивена Сигала",
                "Молодца Арсенецкого",
                "Маркелова",
                "терминатора"
        );
        stringDataGenerator.addPart(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "- часть I",
                "- часть II",
                "2"
                , "3",
                "ремейк",
                 "- начало",
                "в Лас Вентурасе",
                "в Паломино Крик",
                "в салуне Эль Пасо",
                "в портовом борделе",
                "на берегу реки Припять",
                "на небесах",
                "во дворце Садама Хусейна");
        return stringDataGenerator;
    }

    @Bean("filmRatingGenerator")
    public static StringDataGenerator buildFilmRatingGenerator() {
        StringDataGenerator filmRatingGenerator = new StringDataGenerator();
        filmRatingGenerator.addPart("PG-13", "PG-18", "NC-17");
        return filmRatingGenerator;
    }
}
