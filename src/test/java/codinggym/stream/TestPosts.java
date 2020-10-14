package codinggym.stream;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPosts {

    public static Post BLOG_1 = new Post("Solutions Providers, the Pandemic and the Swift Pace of Today’s Digital Products",
            Arrays.asList("Jitin Agarwal", "Ken Gordon"),
            PostType.BLOG,
            LocalDate.of(2020, Month.OCTOBER, 21),
            "https://www.epam.com/insights/blogs/solutions-providers-the-pandemic-and-todays-digital-products",
            143);

    public static Post BLOG_2 = new Post("Looking Ahead to 2021: Transitioning to New Business Models & Learner-Centric Experiences",
            Arrays.asList("Dmitry Krasovskiy"),
            PostType.BLOG,
            LocalDate.of(2020, Month.OCTOBER, 20),
            "https://www.epam.com/insights/blogs/edtech-in-2021-new-business-models-and-learner-centric-experiences",
            107);

    public static Post BLOG_3 = new Post("Leveraging Conversational AI as a Strategic Instrument",
            Arrays.asList("Kshitij Sharma"),
            PostType.BLOG,
            LocalDate.of(2020, Month.OCTOBER, 16),
            "https://www.epam.com/insights/blogs/leveraging-conversational-ai-as-a-strategic-instrument",
            798);

    public static Post BLOG_4 = new Post("The Privacy Web: A Look into Where Personal Data Protection Stands Today and Where It’s Headed",
            Arrays.asList("Mariia Skliarova", "Tymur Sydorenko"),
            PostType.BLOG,
            LocalDate.of(2020, Month.OCTOBER, 7),
            "https://www.epam.com/insights/blogs/the-privacy-web-personal-data-protection-today-and-where-its-headed",
            353);

    public static Post BLOG_5 = new Post("It’s Time for Payors to Scale Intelligent Automation to Improve Interactions with Providers",
            Arrays.asList("Joel Krishnan", "Michael Capofari"),
            PostType.BLOG,
            LocalDate.of(2020, Month.OCTOBER, 6),
            "https://www.epam.com/insights/blogs/its-time-for-payors-to-scale-intelligent-automation",
            342);

    public static Post BLOG_6 = new Post("Designing Professional Learning Across Devices",
            Arrays.asList("Dmitry Krasovskiy"),
            PostType.BLOG,
            LocalDate.of(2020, Month.SEPTEMBER, 30),
            "https://www.epam.com/insights/blogs/designing-professional-learning-across-devices",
            910);

    public static Post BLOG_7 = new Post("Leveraging Gamification for Digital Education and Corporate Learning",
            Arrays.asList("Sergei Diadiura"),
            PostType.BLOG,
            LocalDate.of(2020, Month.SEPTEMBER, 18),
            "https://www.epam.com/insights/blogs/leveraging-gamification-for-digital-education-and-corporate-learning",
            388);

    public static Post BLOG_8 = new Post("Innovate Responsibly: How to Manage Shadow IT without Stifling Transformation",
            Arrays.asList("Ralph Duff", "Boris Khazin"),
            PostType.BLOG,
            LocalDate.of(2020, Month.SEPTEMBER, 16),
            "https://www.epam.com/insights/blogs/how-to-manage-shadow-it-and-innovate-responsibly",
            893);

    public static Post BLOG_9 = new Post("Gaming the System",
            Arrays.asList("Clare Bond"),
            PostType.BLOG,
            LocalDate.of(2020, Month.SEPTEMBER, 8),
            "https://www.epam.com/insights/blogs/gaming-the-system",
            159);

    public static Post INTERVIEW_1 = new Post("EPAM & RTL Group: Delivering a Customer-Centric Over-the-Top (OTT) Media Platform",
            Arrays.asList("Giovanni Piccirilli"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.NOVEMBER, 18),
            "https://www.epam.com/insights/interviews/epam-and-rtl-delivering-a-customer-centric-ott-platform",
            237);

    public static Post INTERVIEW_2 = new Post("Interview with Patrick Allen, Principal, Healthcare Business Consulting at World Healthcare Congress",
            Arrays.asList("Patrick Allen"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.NOVEMBER, 11),
            "https://www.epam.com/insights/interviews/interview-with-patrick-allen-principal-healthcare-business-consulting",
            946);

    public static Post INTERVIEW_3 = new Post("Introducing OSCI: What You Need to Know about the Open Source Contributor Index",
            Arrays.asList("Patrick Stephens"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.NOVEMBER, 6),
            "https://www.epam.com/insights/interviews/what-you-need-to-know-about-the-osci",
            174);

    public static Post INTERVIEW_4 = new Post("How Banks Can Ensure Successful Open Banking & Instant Payments Initiatives",
            Arrays.asList("Alistair Brown"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.OCTOBER, 24),
            "https://www.epam.com/insights/interviews/ensuring-successful-open-banking-and-instant-payments-initiatives",
            582);

    public static Post INTERVIEW_5 = new Post("Interview with Jeff Kim, Managing Principal, Life Sciences Consulting, at BioProcess International US 2019",
            Arrays.asList("Jeff Kim"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.OCTOBER, 10),
            "https://www.epam.com/insights/interviews/interview-with-jeff-kim-life-sciences-consultant",
            567);

    public static Post INTERVIEW_6 = new Post("Is an API-First Development Approach Right for Your Business?",
            Arrays.asList("Pavel Veller"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.AUGUST, 13),
            "https://www.epam.com/insights/interviews/is-an-api-first-development-approach-right-for-your-business",
            450);

    public static Post INTERVIEW_7 = new Post("EPAM Wealth Management Consultants Interviewed at WealthBriefing Singapore",
            Arrays.asList("Glenn Hempel", "Heiko Sundermann"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.JULY, 15),
            "https://www.epam.com/insights/interviews/epam-wealth-management-consultants-interviewed-at-wealthbriefing-singapore",
            919);

    public static Post INTERVIEW_8 = new Post("Balazs Fejes Weighs in on Digital Transformation in Banking at Frankfurt Finance Summit 2019",
            Arrays.asList("Balazs Fejes"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.JULY, 12),
            "https://www.epam.com/insights/interviews/balazs-fejes-weighs-in-on-digital-transformation-in-banking-at-frankfurt-finance-summit-2019",
            117);

    public static Post INTERVIEW_9 = new Post("In Pursuit of Instant Payments: The Latest From London",
            Arrays.asList("Alistair Brown"),
            PostType.INTERVIEW,
            LocalDate.of(2019, Month.MAY, 2),
            "https://www.epam.com/insights/interviews/in-pursuit-of-instant-payments-the-latest-from-london",
            254);

    public static Post PODCAST_1 = new Post("Silo Busting 9: Securing the Cloud with Sam Rehman",
            Arrays.asList("Sam Rehman"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.OCTOBER, 14),
            "https://www.epam.com/insights/podcasts/silo-busting-9-securing-the-cloud-with-sam-rehman",
            195);

    public static Post PODCAST_2 = new Post("The Pressure of Having to Learn New Technologies on the Fly, in Front of Other People… That’s Just a Whole Other Layer of Stress",
            Arrays.asList("Sandra Loughlin", "Jitin Agarwal"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.AUGUST, 25),
            "https://www.epam.com/insights/podcasts/reskilling-in-the-next-normal-with-sandra-loughlin-and-jitin-agarwal",
            426);

    public static Post PODCAST_3 = new Post("Silo Busting 6: Cybersecurity During the Pandemic with Sam Rehman and Jitin Agarwal",
            Arrays.asList("Sam Rehman", "Jitin Agarwal"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.AUGUST, 13),
            "https://www.epam.com/insights/podcasts/cybersecurity-during-the-pandemic-with-sam-rehman-and-jitin-agarwal",
            489);

    public static Post PODCAST_4 = new Post("The Resonance Test: Digital Epidemiologist John Brownstein Discusses COVID-19’s Impact on the Healthcare Industry with Jonathon Swersey",
            Arrays.asList("John Brownstein", "Jonathon Swersey"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.AUGUST, 3),
            "https://www.epam.com/insights/podcasts/epidemiologist-john-brownstein-talks-covid-19s-impact-on-healthcare",
            333);

    public static Post PODCAST_5 = new Post("Silo Busting 4: Retail After Lockdown with Buck Sleeper and Jitin Agarwal",
            Arrays.asList("Buck Sleeper", "Jitin Agarwal"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.JUNE, 9),
            "https://www.epam.com/insights/podcasts/retail-after-lockdown-with-buck-sleeper-and-jitin-agarwal",
            115);

    public static Post PODCAST_6 = new Post("How Intelligent Automation Puts People First",
            Arrays.asList("Albert Rees", "Chris Michaud"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.APRIL, 10),
            "https://www.epam.com/insights/podcasts/how-intelligent-automation-puts-people-first",
            327);

    public static Post PODCAST_7 = new Post("Adaptive Enterprise Series: Episode 1: Elaina Shekhter & Guest Speaker, Allen Bonde",
            Arrays.asList("Elaina Shekhter", "Allen Bonde"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.MARCH, 20),
            "https://www.epam.com/insights/podcasts/adaptive-enterprise-series-episode-1",
            251);

    public static Post PODCAST_8_DUPLICATE = new Post("The Resonance Test 44: Joanne Chang",
            Arrays.asList("Buck Sleeper", "Ken Gordon", "Joanne Chang"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.FEBRUARY, 26),
            "https://www.epam.com/insights/podcasts/joanne-chang-on-designing-a-delicious-customer-experience",
            81);

    public static Post PODCAST_9_DUPLICATE = new Post("Author Gary Pisano Shares How Companies Can Prioritize Innovation",
            Arrays.asList("Jon Campbell", "Ken Gordon", "Gary Pisano"),
            PostType.PODCAST,
            LocalDate.of(2020, Month.FEBRUARY, 7),
            "https://www.epam.com/insights/podcasts/joanne-chang-on-designing-a-delicious-customer-experience",
            406);

    public static List<Post> BLOGS = Arrays.asList(BLOG_1, BLOG_2, BLOG_3, BLOG_4, BLOG_5, BLOG_6, BLOG_7,
            BLOG_8, BLOG_9);

    public static List<Post> INTERVIEWS = Arrays.asList(INTERVIEW_1, INTERVIEW_2, INTERVIEW_3, INTERVIEW_4,
            INTERVIEW_5, INTERVIEW_6, INTERVIEW_7, INTERVIEW_8, INTERVIEW_9);

    public static List<Post> PODCASTS = Arrays.asList(PODCAST_1, PODCAST_2, PODCAST_3, PODCAST_4, PODCAST_5, PODCAST_6, PODCAST_7,
            PODCAST_8_DUPLICATE, PODCAST_9_DUPLICATE);

    // problem01
    // Собрать посты из BLOGS, INTERVIEWS, PODCASTS в один список POSTS.
    public static List<Post> POSTS = Stream.of(BLOGS, INTERVIEWS, PODCASTS)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
}
