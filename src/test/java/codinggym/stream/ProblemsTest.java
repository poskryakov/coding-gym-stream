package codinggym.stream;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static codinggym.stream.TestPosts.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ProblemsTest {

    @Test
    @DisplayName("01 Собрать три списка в один")
    void problem01() {
        // Задачу нужно выполнить в файле src\test\java\codinggym\stream\ProblemsTest.java (см. конец файла)
        // Собрать посты из BLOGS, INTERVIEWS, PODCASTS в один список ProblemsTest.POSTS.

        // Check
        assertTrue(POSTS.containsAll(BLOGS));
        assertTrue(POSTS.containsAll(INTERVIEWS));
        assertTrue(POSTS.containsAll(PODCASTS));
        POSTS.forEach(post -> assertTrue(BLOGS.contains(post) || INTERVIEWS.contains(post) || PODCASTS.contains(post)));
    }
    // Мораль. Прошли три стандартных шага в работе стрима: создать стрим, преобразовать данные, собрать результат.

    @Test
    @DisplayName("02 Хорошие, свежие посты")
    void problem02() {
        // Вернуть список постов, которые набрали более 500 лайков, среди опубликованных за последние 3 месяца.
        // Сортировать список от самого свежего поста к самому старому.
        LocalDate NOW = LocalDate.of(2020, Month.OCTOBER, 26);
        List<Post> expected = Arrays.asList(BLOG_3, BLOG_6, BLOG_8);

        // Solution
        List<Post> result = POSTS.stream()
                .filter(post -> post.getLikes() > 500 && post.getDate().isAfter(NOW.minusMonths(3)))
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .collect(Collectors.toList());

        // Check
        assertEquals(expected, result);
    }
    // Мораль. Stream API и Date and Time API появились в Java 8.
    // Мораль. У интерфейса Comparator есть default методы, которые помогают конструировать компараторы.

    @Test
    @DisplayName("03 Заголовки и URL в порядке")
    void problem03() {
        // Проверить, что у всех постов есть непустой заголовок и url находится на сайте EPAM.
        // Использовать готовые предикаты.

        // Solution
        Predicate<Post> isTitleNull = bp -> bp.getTitle() == null;
        Predicate<Post> isTitleBlank = bp -> bp.getTitle().trim().isEmpty();
        Predicate<Post> isUrlEpam = bp -> "www.epam.com".equalsIgnoreCase(bp.getUrl().getHost());

        boolean result = POSTS.stream()
                .allMatch(isTitleNull.negate().and(isTitleBlank.negate()).and(isUrlEpam));

        // Check
        assertTrue(result);
    }
    // Мораль. У Predicate и других интерфейсов есть полезные default методы.

    @Test
    @DisplayName("04 Дубликаты")
    void problem04() {
        // Собрать все дубликаты. Дубликатами мы считаем два (и более) поста с одинаковыми URL.
        // Map.of(K, V, K, V, K, V) появился в Java 9
        Map<String, Set<Post>> expected = Map.of("https://www.epam.com/insights/podcasts/joanne-chang-on-designing-a-delicious-customer-experience",
                Set.of(PODCAST_9_DUPLICATE, PODCAST_8_DUPLICATE));

        // Solution
        Map<String, Set<Post>> result = POSTS.stream()
                .collect(Collectors.groupingBy(Post::getUrl, Collectors.toSet()))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), entry -> entry.getValue()));

        // Check
        assertEquals(expected, result);
    }

    /*
    * Вопрос для чата.
    * What will the following code print?
    *
    * List<Integer> ls = Arrays.asList(1, 2, 3);
    * Function<Integer, Integer> func = a -> a * a;  //1
    * ls.stream().map(func).peek(System.out::print); //2
    *
    * 1) Compilation error at //1
    *
    * 2) Compilation error at //2
    *
    * 3) 149
    *
    * 4) 123
    *
    * 5) It will compile and run fine but will not print anything.
    *
    * Правильный ответ: 5.
    * Стрим не запустится, так как нет терминальной операции.
    * */

    @Test
    @DisplayName("05 Поддержим лайками")
    void problem05() {
        // Поддержим лайками. Найти 3 наименее залайканных поста, добавить каждому 100 лайков,
        // вернуть список этих постов. Затем обязательно(!) вернуть их количество лайков к исходному.
        List<Post> expected = Arrays.asList(PODCAST_8_DUPLICATE, BLOG_2, PODCAST_5);

        // Solution
        List<Post> result = POSTS.stream()
                .sorted(Comparator.comparing(Post::getLikes))
                .limit(3)
                .peek(post -> post.setLikes(post.getLikes() + 100))
                .collect(Collectors.toList());

        result.forEach(post -> post.setLikes(post.getLikes() - 100));

        // Check
        assertEquals(expected, result);
    }
    // Мораль. peek используют, когда не нужно заменять элементы стрима, map - когда нужно заменить.
    // Мораль. Стрим не запустится без терминальной операции - один только peek не сработает.

    @Test
    @DisplayName("06 Последний элемент стрима")
    void problem06() {
        // Вернуть последний элемент стрима.
        Post expected = PODCAST_9_DUPLICATE;

        // Solution
        Post result = POSTS.stream()
                .reduce((accumulator, next) -> next)
                .orElseThrow();

        // Check
        assertEquals(expected, result);
    }
    // Мораль. orElseThrow() без аргументов выкидывает NoSuchElementException.
    // Мораль. Это сработало для стрима конечного размера. Но бывают и бесконечные стримы, порождаемые методами
    //   iterate и generate. Осторожнее с ними.
    // Мораль. Есть 2 варианта reduce: без начального элемента (возвращает Optional) и с начальным (возвращает элемент).

    /*
    * Вопрос для чата.
    * Given that a method named Double getPrice(String id) exists and may potentially return null, about which of the
    * following options can you be certain that a run time exception will not be thrown?
    *
    * You had to select 2 options
    *
    * 1) Optional<Double> price = Optional.of(getPrice("1111"));
    *
    * 2) Optional<Double> price = Optional.ofNullable(getPrice("1111"));
    *    Double x = price.orElse(getPrice("2222"));
    *
    * 3) Optional<Double> price = Optional.ofNullable(getPrice("1111"));
    *    Double y = price.orElseGet(()->getPrice("333"));
    *
    * 4) Optional<Double> price = Optional.of(getPrice("1111"), 10.0);
    *
    * 5) Optional<Double> price = Optional.of(getPrice("1111"));
    *    Double z = price.orElseThrow(()->new RuntimeException("Bad Code"));
    *
    * Правильный ответ: 2, 3.
    * В вариантах 1 и 5 можно получить NPE в Optional.of().
    * В варианте 4 будет ошибка компиляции - нет метода Optional.of() с двумя параметрами.
    * */

    @Test
    @DisplayName("07 Сумма лайков по категориям")
    void problem07() {
        // Вернуть EnumMap содержащий сумму лайков в каждой категории PostType.
        // Map.of(K, V, K, V, K, V) - Java 9
        Map<PostType, Integer> expected = Map.of(
                PostType.BLOG, 4093,
                PostType.INTERVIEW, 4246,
                PostType.PODCAST, 2623);

        // Solution
        Map<PostType, Integer> result = POSTS.stream()
                .collect(Collectors.groupingBy(Post::getType, () -> new EnumMap<>(PostType.class), Collectors.summingInt(Post::getLikes)));

        // Check
        assertEquals(expected, result);
    }
    // Мораль. Коллекторы можно удобно собирать в цепочки благодаря downstream коллекторам.
    // Мораль. В коллекторах можно заменить тип коллекции. Например, HashMap на ConcurrentHashMap или TreeMap.

    @Test
    @DisplayName("08 Авторы-чемпионы")
    void problem08() {
        // Найти авторов, у которых 3 и более постов и их лучший пост (наибольший по лайкам).
        // Вернуть результат в формате Map: <Имя автора>:<Его лучший пост>. См. пример expected.
        // Примечание: у поста может быть несколько авторов.
        Map<String, Post> expected = Map.of("Ken Gordon", PODCAST_9_DUPLICATE, "Jitin Agarwal", PODCAST_3);

        // Solution
        class AuthorPost {
            String author;
            Post post;

            public AuthorPost(String author, Post post) {
                this.author = author;
                this.post = post;
            }
        }

        Map<String, Post> result = POSTS.stream()
                .flatMap(post -> post.getAuthors().stream().map(author -> new AuthorPost(author, post)))
                .collect(Collectors.groupingBy(ap -> ap.author, Collectors.mapping(ap -> ap.post, Collectors.toList())))
                .entrySet().stream()
                .filter(authorPosts -> authorPosts.getValue().size() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        authorPosts -> authorPosts.getValue().stream().max(Comparator.comparing(Post::getLikes)).orElseThrow()));

        // Check
        assertEquals(expected, result);
    }
    // Мораль. Про пары. В Java 6+ можно использовать AbstractMap.SimpleEntry или AbstractMap.SimpleImmutableEntry.
    //   В Java 9+ есть метод Map.entry() для создания immutable пары. В Apache Commons есть класс Pair.
    // Мораль. Вероятно, можно упростить с помощью коллектора Collectors.filtering. Но он появился только в Java 9.

    @Test
    @DisplayName("09 Новые авторы")
    void problem09() {
        // В файлах директории src/test/resources/authors находятся старые списки авторов.
        // Требуется
        // 1. Прочитать всех старых авторов из всех файлов и сложить в одну коллекцию.
        // 2. Сравнить старый список авторов с актуальным (из POSTS) и вернуть Set новых авторов.
        //    "Новый автор" - тот, кто есть в актуальном списке, но отсутствуют в старом.
        // Примечание. У поста может быть несколько авторов.
        // Примечание. Стримы из Files.list и Files.lines - это редкий вид стримов, которые нужно закрывать
        //   самостоятельно или в try-with-resources, чтобы освободить ресурсы ввода-вывода. Они не закроются сами.
        Set<String> expected = new HashSet<>(Arrays.asList("Patrick Allen", "Dmitry Krasovskiy"));

        // Solution
        Function<Path, Stream<Path>> listFilesInDirectory = path -> {
            try (Stream<Path> filesStream = Files.list(path)) {
                return filesStream.collect(Collectors.toSet()).stream();
            } catch (IOException e) {
                return Stream.empty();
            }
        };

        Function<Path, Stream<String>> readLinesFromFile = path -> {
            try (Stream<String> linesStream = Files.lines(path)) {
                return linesStream.collect(Collectors.toSet()).stream();
            } catch (IOException e) {
                return Stream.empty();
            }
        };

        Set<String> oldAuthors = listFilesInDirectory.apply(Paths.get("src/test/resources/authors"))
                .flatMap(readLinesFromFile)
                .collect(Collectors.toSet());

        Set<String> result = POSTS.stream()
                .flatMap(post -> post.getAuthors().stream())
                .filter(author -> !oldAuthors.contains(author))
                .collect(Collectors.toSet());

        // Check
        assertEquals(expected, result);
    }
    // Мораль. Стрим Files.lines() можно использовать в параллельном режиме. Чтение файла ускорится.
    // Мораль. Стримы имеют метод close() и реализуют интерфейс AutoCloseable. Но стримы, которые создаются из
    //   коллекций, массивов и генерирующих функций, не требуется закрывать после использования.
    //
    //   В JDK необходимо закрывать только стримы, которые используют каналы ввода-вывода (IO channels). Например,
    //   Files.lines() требуется закрывать самостоятельно, вызвав close() или объявив его в блоке try-with-resources.
    //
    //   При этом закрывать стрим Files.lines() в промежуточных операциях нельзя. Ведь тогда терминальная операция
    //   не сможет из него прочитать и выбросит исключение IllegalStateException. Можно, например, прочитать строки
    //   во временную коллекцию и использовать ее далее в стриме.

    /*
    * Вопрос для чата
    * Identify correct statements about the following code:
    *
    * List<String> vals = Arrays.asList("a", "b");
    * String join = vals.parallelStream()
    *          .reduce("_", (a, b)->a.concat(b));
    * System.out.println(join);
    *
    * You had to select 1 option
    *
    * 1) It will always print _ab
    *
    * 2) It will always print either _ab or _ba
    *
    * 3) It will print either _ab or _a_b
    *
    * 4) It will print any of the following: _ab, _ba, _a_b,_b_a
    *
    * Правильный ответ: 3.
    * Обратите внимание, что этот стрим параллельный (parallel) и упорядоченный (ordered).
    *
    * Стрим параллельный, значит он теоретически может выполняться в 2-х или в 1-ом потоке. Если выполняется в
    * двух потоках, то первое выполнение reduce дает "_a" в первом потоке и "_b" во втором потоке, затем их объединяют
    * в "_a_b". Если выполняется в одном потоке, то "b" прибавляется к "_a" - в результате "_ab".
    *
    * Стрим упорядоченный, так как получен из упорядоченной коллекции List (не из Set, например). Терминальная операция
    * reduce сохраняет порядок исходной коллекции: сначала результат обработки "a", затем результат обработки "b".
    * Поэтому невозможен ответ (4), где буквы идут в другом порядке. Кстати, терминальная операция forEach на
    * параллельном стриме не сохраняет порядок, для сохранения есть forEachOrdered.
    * */

    @Test
    @DisplayName("10 Коллекторы filtering и teeing")
    void problem10() {
        // Познакомимся с коллекторами filtering и teeing, они появились в Java 12.
        // Собрать посты, чьи заголовки начинаются с "A" и "E" в указанном ниже формате.

        // Что добавили после Java 8?
        // Java 9. Появились методы takeWhile и dropWhile,  коллекторы flatMapping, filtering.
        // Java 10. Появились коллекторы toUnmodifiableList, toUnmodifiableSet, toUnmodifiableMap.
        // Java 12: Появился коллектор teeing.

        // Map.of(K, V, K, V, K, V) появился в Java 9.
        Map<String, List<Post>> expected = Map.of(
                "Start with A", List.of(PODCAST_7, PODCAST_9_DUPLICATE),
                "Start with E", List.of(INTERVIEW_1, INTERVIEW_7));

        // Solution
        Map<String, List<Post>> result = POSTS.stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(post -> post.getTitle().startsWith("A"), Collectors.toList()),
                        Collectors.filtering(post -> post.getTitle().startsWith("E"), Collectors.toList()),
                        (listA, listB) -> Map.of("Start with A", listA, "Start with E", listB)));

        // Check
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("11 Коллектор своими руками")
    void problem11() {
        // Собрать все посты в unmodifiable Set с помощью кастомного коллектора. Используйте только Java 8,
        // удобно использовать метод Collector.of().
        // Примечание. В Java 10 появились коллекторы toUnmodifiableList, toUnmodifiableSet, toUnmodifiableMap.

        // Solution
        Collector<Post, Set<Post>, Set<Post>> unmodifiableSetCollector = Collector.of(
                HashSet::new,
                Set::add,
                (leftPart, rightPart) -> { leftPart.addAll(rightPart); return leftPart; },
                Collections::unmodifiableSet,
                Collector.Characteristics.UNORDERED);

        Set<Post> result = POSTS.stream()
                .collect(unmodifiableSetCollector);

        // Check
        assertTrue(result instanceof Set);
        assertThrows(UnsupportedOperationException.class, () -> result.add(null));
    }

    /*
     * Вопрос для чата
     * Given:
     *
     * Stream<Integer> strm1 = Stream.of(2, 3, 5, 7, 11, 13, 17, 19);     //1
     * Stream<Integer> strm2 = strm1.filter(i->{ return i>5 && i<15; });  //2
     * strm2.forEach(System.out::print);                                  //3
     *
     * Which of the following options can be used to replace line at //2 and still print the same elements of the stream?
     *
     * You had to select 1 option
     *
     * 1) Stream<Integer> strm2 = strm1.filter(i>5).filter(i<15);
     *
     * 2) Stream<Integer> strm2 = strm1.parallel().filter(i->i>5).filter(i->i<15).sequential();
     *
     * 3) Stream<Integer> strm2 = strm1.collect(Collectors.partitioningBy(i->{ return i>5 && i<15; })).get("true").stream();
     *
     * 4) Stream<Integer> strm2 = strm1.map(i-> i>5?i<15?i:null:null);
     *
     * Правильный ответ: 2.
     * Вариант 1 не компилируется, "i>5" и "i<15" - синтаксически не лямбда-выражения.
     * Вариант 2 правильный - стрим выполнится последовательно. Дело в том, что parallel() и sequential() не управляют
     *   тем, как выполняются отдельные операции. Они только переключают флаг в свойствах стрима. Здесь такая
     *   последовательность действий: создается последовательный стрим, затем переключается в параллельный режим,
     *   затем переключается в последовательный режим. К моменту терминальной операции стрим последовательный.
     *   Не важно каков стрим на промежуточных операциях, важно только каков он к моменту терминальной операции.
     *   Терминальная операция forEach обрабатывает последовательные упорядоченные стримы в порядке следования
     *   элементов. Кстати, forEach на параллельном стриме не сохранит порядок, для сохранения есть forEachOrdered.
     * Вариант 3 даст NullPointerException из-за get("true"). Коллектор partitioningBy создает Map с ключами true и
     *   false типа Boolean (а не типа String). Поэтому get("true") вернет null.
     * Вариант 4 выведет "nullnullnull71113nullnull".
     * */

    @Test
    @DisplayName("12 Параллельный стрим")
    void problem12() {
        // Задача из трех шагов
        // 1. Загрузить в параллельном стриме страницы по url с помощью Post.fetchPage.
        //    Вывести на консоль:
        //      Суммарный размер полученных страниц.
        // 2. Выполнить стрим из пункта (1) в собственном отдельном thread pool, а не в commonPool. Убедиться,
        //    что стрим действительно выполняется в отдельном thread pool.
        // 3. Дополнительно вывести на консоль:
        //      Время выполнения стрима (в основном потоке).
        //      Суммарное время выполнения запросов (в потоках thread pool).
        //    Можно использовать класс Clock из Date and Time API, он появился в Java 8.

        // Solution
        int totalLength = 0;
        long streamDuration = 0;
        AtomicLong sumOfDurations = new AtomicLong();

        Clock clock = Clock.systemDefaultZone();
        ForkJoinPool customFJP = new ForkJoinPool(4);
        Callable<Integer> task = () -> POSTS.parallelStream()
                .mapToInt(post -> {
                    assertEquals(customFJP, ForkJoinTask.getPool());
                    long start = clock.millis();
                    int length = post.fetchPage().length();
                    sumOfDurations.addAndGet(clock.millis() - start);
                    return length; })
                .sum();

        long streamStart = clock.millis();
        ForkJoinTask<Integer> fjTask = customFJP.submit(task);
        try {
            totalLength = fjTask.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        streamDuration = clock.millis() - streamStart;

        System.out.println("Суммарный размер полученных страниц: " + totalLength + " символов");
        System.out.println("Время выполнения стрима (в основном потоке): " + streamDuration + " мс");
        System.out.println("Суммарное время выполнения запросов (в потоках thread pool): " + sumOfDurations + " мс");
    }
    // Мораль. Можно запускать параллельные стримы в нашем собственном пуле потоков, а не в системном.
    // Мораль. Помним о thread-safe и используем Atomic* классы, когда используем параллельные стримы.

    @Test
    @DisplayName("13 CompletableFuture")
    void problem13() {
        // Загрузить в непараллельном(!) стриме страницы по url с помощью Post.fetchPageAsync.
        // Вывести на консоль:
        //   Суммарный размер полученных страниц.

        // Solution
        AtomicInteger totalLength = new AtomicInteger();

        CompletableFuture[] futuresArray = POSTS.stream()
                .map(Post::fetchPageAsync)
                .map(cf -> cf.thenApply(String::length))
                .map(cf -> cf.thenAccept(totalLength::addAndGet))
                .toArray(CompletableFuture[]::new);

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futuresArray);
        combinedFuture.join();

        System.out.println("Суммарный размер полученных страниц: " + totalLength + " символов");
    }

    // Мораль. Важно соединить CompletableFuture с помощью allOf() перед тем, как вызывать join(). Ведь если мы
    //   будем вызывать join() для каждого из них отдельно, то это будут последовательные ожидания и суммарное
    //   время ожидания значительно вырастет.
    // Мораль. Помним о thread-safe и используем Atomic* классы, когда используем CompletableFuture.
}
