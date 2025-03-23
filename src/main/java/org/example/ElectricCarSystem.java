package org.example;

import java.util.concurrent.locks.ReentrantLock;

// Lớp mô phỏng hệ thống xe điện trẻ em
class ElectricCar {
  private String direction = "IDLE"; // Trạng thái xe
  private final ReentrantLock lock = new ReentrantLock(); // Khóa đồng bộ

  // Điều khiển di chuyển (DC Motor)
  public void move(String newDirection) {
    lock.lock(); // Khóa tài nguyên
    try {
      direction = newDirection;
      System.out.println("Car is moving: " + direction);
      Thread.sleep(1000); // Giả lập delay
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock(); // Mở khóa sau khi hoàn thành
    }
  }

  // Điều khiển tay lái (Encoded Motor)
  public synchronized void steer(String turn) {
    System.out.println("Steering " + turn);
    try {
      Thread.sleep(500); // Giả lập hành động
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // Lấy trạng thái hiện tại của xe
  public synchronized String getDirection() {
    return direction;
  }
}

// Lớp mô phỏng điều khiển từ xa
class RemoteControl implements Runnable {
  private ElectricCar car;

  public RemoteControl(ElectricCar car) {
    this.car = car;
  }

  @Override
  public void run() {
    String[] commands = {"FORWARD", "LEFT", "RIGHT", "BACKWARD", "STOP"};
    for (String command : commands) {
      car.move(command);
      try {
        Thread.sleep(1500); // Giả lập delay giữa các lệnh
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

// Lớp mô phỏng điều khiển động cơ lái
class SteeringControl implements Runnable {
  private ElectricCar car;

  public SteeringControl(ElectricCar car) {
    this.car = car;
  }

  @Override
  public void run() {
    String[] turns = {"LEFT", "RIGHT", "CENTER"};
    for (String turn : turns) {
      car.steer(turn);
      try {
        Thread.sleep(700); // Giả lập delay giữa các thao tác
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

// Chương trình chính
public class ElectricCarSystem {
  public static void main(String[] args) {
    ElectricCar car = new ElectricCar();

    // Tạo hai luồng xử lý song song
    Thread remoteThread = new Thread(new RemoteControl(car));
    Thread steeringThread = new Thread(new SteeringControl(car));

    // Chạy các luồng
    remoteThread.start();
    steeringThread.start();

    try {
      remoteThread.join();
      steeringThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Final Car Direction: " + car.getDirection());
  }
}

