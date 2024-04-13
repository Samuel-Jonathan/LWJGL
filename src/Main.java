import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    private long window;

    public void run() {
        init();
        loop();

        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
    }

    private void init() {
        // Initialiser GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Impossible d'initialiser GLFW");
        }

        // Configuration de GLFW
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // Masquer la fenêtre initialement
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); // Autoriser la redimension de la fenêtre

        // Créer la fenêtre
        window = GLFW.glfwCreateWindow(800, 600, "Fenêtre LWJGL", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Échec de création de la fenêtre GLFW");
        }

        // Centrer la fenêtre sur l'écran
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        assert vidMode != null;
        GLFW.glfwSetWindowPos(window, (vidMode.width() - 800) / 2, (vidMode.height() - 600) / 2);

        // Rendre le contexte OpenGL courant
        GLFW.glfwMakeContextCurrent(window);
        // Activer la synchronisation verticale
        GLFW.glfwSwapInterval(1);

        // Rendre la fenêtre visible
        GLFW.glfwShowWindow(window);

        // Rendre le contexte OpenGL courant
        GL.createCapabilities();
    }

    private void loop() {
        // Définir la couleur de fond
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // Exécuter la boucle de rendu jusqu'à ce que l'utilisateur tente de fermer la fenêtre
        while (!GLFW.glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Effacer le framebuffer

            GLFW.glfwSwapBuffers(window); // Échanger les tampons de couleur

            // Attendre les événements de la fenêtre. Le rappel de touche ci-dessus ne sera invoqué que pendant cet appel.
            GLFW.glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
