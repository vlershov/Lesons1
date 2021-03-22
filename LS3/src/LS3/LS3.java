package LS3;

public class LS3 {

        static volatile char c = 'A';
        static Object mon = new Object();

        static class WaitNotifyClass implements Runnable {
            private char currentLetter;
            private char nextLetter;

            public WaitNotifyClass(char currentLetter, char nextLetter) {
                this.currentLetter = currentLetter;
                this.nextLetter = nextLetter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        try {
                            while (c != currentLetter)
                                mon.wait();
                            System.out.print(currentLetter);
                            c = nextLetter;
                            mon.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        public static void main(String[] args) {
            System.out.println("Задача 1");
            new Thread(new WaitNotifyClass('A', 'B')).start();
            new Thread(new WaitNotifyClass('B', 'C')).start();
            new Thread(new WaitNotifyClass('C', 'A')).start();
        }

}
