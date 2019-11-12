package com.sosoMobie;

import javax.sql.rowset.spi.SyncResolver;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <H1>项目名称: 移动业务大厅 </H1>
 * <H2>文件名称: Main.java </H2>
 * <p>描述: [主类，包括所有一级菜单和二级菜单的实现，及主函数] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public static void main(String[] args)</li>
 *     <li>private static void mainMenu(CardUtil cardUtil)</li>
 *     <li>private static void subMenu1(CardUtil cardUtil, String card)</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class Main {
    
    /**
     * <H2> Main.main(String[] args) </H2>
     * 该方法为入口点，执行方法类初始化并进入一级菜单
     * @param args 命令行参数，String[]
     */
    public static void main(String[] args) {
        //初始化工具类
        CardUtil util = new CardUtil();
        Main.mainMenu(util);
    }
    
    /**
     * <H2>private static void mainMenu(CardUtil cardUtil)</H2><br>
     * 该方法为一级菜单，使用do...while循环反复执行菜单栏选择。<br>
     * 输入1：输入并检查用户名密码合法性后，调用二级菜单方法submenu1，传入方法类与卡号<br>
     * 输入2：调用 CardUtil.getNewNumbers(int) 方法，并将结果格式化输出，每行3个，格式控制符tab分割<br>
     *          用户完成选择之后，执行信息录入，检查预存话费数值合法性后调用 cardUtil.addCard 添加
     *          添加成功后打印用户信息，调用 MobileCard.showMeg() 与 MobileCard.serPackage.showInfo() 方法<br>
     * 输入3：输入卡号并调用合法性后，调用 CardUtil.useSoso() 方法<br>
     * 输入4：输入卡号、金额并检查数值合法性后，调用 cardUtil.chargeMoney(String, int) 方法<br>
     * 输入5：调用 ardUtil.showDescription() 方法<br>
     * 输入6：输出欢迎信息后退出<br>
     * 其他输入：提示后重新输入<br>
     * @param cardUtil 工具类，CardUtil
     */
    private static void mainMenu(CardUtil cardUtil){
        Scanner scanner = new Scanner(System.in);
        int select = -1;

        do{
            System.out.print("\n********************欢迎使用嗖嗖移动业务大厅********************\n" +
                    "1.用户登录 2.用户注册 3.使用嗖嗖 4.话费充值 5.资费说明 6.退出系统\n" +
                    "请选择：");
            try {
                select = scanner.nextInt();
            }
            catch(InputMismatchException e){
                scanner = new Scanner(System.in);
                System.out.println("请输入正确的数字");
            }

            switch (select)
            {
                case 1:
                    String card, password;
                    boolean flag;

                    System.out.print("请输入手机卡号：");
                    card = scanner.next();
                    flag = cardUtil.isExistCard(card);
                    if(flag){   //先检查卡号是否存在，验证存在后再输入密码
                        System.out.print("请输入密码：");
                        password = scanner.next();
                        flag = cardUtil.isExistCard(card, password);
                        if(flag){   //再检查密码是否正确
                            System.out.println("登录成功");
                            subMenu1(cardUtil, card);   //进入二级菜单
                        }
                        else{
                            System.out.println("密码输入错误，请重试");
                        }
                    }
                    else{
                        System.out.println("手机卡号不存在，请重试");
                    }
                    break;

                case 2:
                    MobileCard newCard = new MobileCard();
                    int numIndex = -1, numberCount = 9;

                    System.out.println("*****可选的卡号*****");
                    String[] numbers = cardUtil.getNewNumbers(numberCount);
                    int cntLine = 0;    //该变量用于计数卡号数量，决定是否换行
                    for(int i = 0; i < numbers.length; i++, cntLine++){
                        if(cntLine != 0)
                            System.out.print("\t");
                        System.out.print(i + 1 + "." + numbers[i]);
                        if(cntLine == 2){
                            cntLine = -1;
                            System.out.println();
                        }
                    }
                    do{
                        System.out.print("请选择卡号(输入1~9的序号)：");
                        try{
                            numIndex = scanner.nextInt();
                        }
                        catch (InputMismatchException e){
                            scanner = new Scanner(System.in);
                            numIndex = -1;
                        }
                    } while(numIndex < 1 || numIndex > numberCount);    //循环检查输入是否合法
                    newCard.cardNumber = numbers[numIndex];

                    do{
                        System.out.print("1.话痨套餐 2.网虫套餐 3.超人套餐， 请选择套餐(输入序号)：");
                        try{
                            numIndex = scanner.nextInt();
                        }
                        catch (InputMismatchException e){
                            scanner = new Scanner(System.in);
                            numIndex = -1;
                        }
                    } while(numIndex < 1 || numIndex > 3);    //循环检查输入是否合法
                    if(numIndex == 1)   //通话、上网、超级套餐分别代号1、2、3
                        newCard.serPackage = new TalkPackage();
                    else if(numIndex == 2)
                        newCard.serPackage = new NetPackage();
                    else
                        newCard.serPackage = new SuperPackage();

                    System.out.print("请输入姓名：");
                    newCard.userName = scanner.next();

                    System.out.print("请输入密码：");
                    newCard.password = scanner.next();

                    double preMoney = -1;
                    boolean tempFlag = true;
                    do{
                        System.out.print("请输入预存话费金额：");
                        if(!tempFlag)
                            System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值：");
                        try{
                            preMoney = scanner.nextDouble();
                        }
                        catch (InputMismatchException e){
                            scanner = new Scanner(System.in);
                            System.out.println("请输入正确的数字");
                        }
                        tempFlag = ! (preMoney < newCard.serPackage.price) || ! (preMoney > 0);
                    } while(preMoney <= 0 || preMoney < newCard.serPackage.price);    //循环检查输入是否合法
                    
                    //直接扣除首月套餐费再存入
                    newCard.money = preMoney - newCard.serPackage.price;

                    cardUtil.addCard(newCard);
                    System.out.print("注册成功！");
                    newCard.showMeg();
                    newCard.serPackage.showInfo();

                    break;

                case 3:
                    String num;
                    do{
                        System.out.print("请输入手机卡号：");
                        num = scanner.next();
                        if(!cardUtil.isExistCard(num))
                            System.out.println("手机卡号不存在，请重试");
                        else
                            break;
                    } while(true);
                    cardUtil.useSoso(num);
                    break;

                case 4:
                    System.out.print("请输入充值卡号：");
                    String number = scanner.next();

                    if(cardUtil.isExistCard(number)){
                        double mount = -1;
                        do{
                            System.out.print("请输入充值金额：");
                            try{
                                mount = scanner.nextDouble();
                            }
                            catch (InputMismatchException e){
                                scanner = new Scanner(System.in);
                                System.out.println("请输入正确的数字");
                            }
                        } while(mount <= 0);    //循环检查输入是否合法

                        if (mount < 50){
                            System.out.println("充值金额至少为50元");
                        }
                        else{
                            cardUtil.chargeMoney(number, mount);
                        }
                    }
                    else{
                        System.out.println("充值卡号不存在，请重试");
                    }
                    break;

                case 5:
                    cardUtil.showDescription();
                    break;

                case 6:
                    System.out.println("感谢使用嗖嗖移动业务大厅"); //退出
                    return;

                default:
                    System.out.println("请在1-6中做出选择");   //对于任意其他输入，显示提示信息后重新输入
            }
        } while(true);  //循环执行主要方法体
    }

  /**
   * <H2>private static void mainMenu(CardUtil cardUtil)</H2><br>
   * 该方法为一级菜单，使用do...while循环反复执行菜单栏选择。<br>
   * 输入1：调用 CardUtil.showAmountDetail(String) 方法<br>
   * 输入2：调用 CardUtil.showRemainDetail(String) 方法<br>
   * 输入3：调用 cardUtil.printConsumInfo(String) 方法<br>
   * 输入4：输入选择目标套餐并检查数值合法性后，调用 CardUtil.changingPack(String, int) 方法<br>
   * 输入5：调用 CardUtil.delCard(String) 方法<br>
   * 其他输入：返回上一级<br>
   * @param cardUtil 工具类，CardUtil
   * @param card 卡号，String
   */
  private static void subMenu1(CardUtil cardUtil, String card) {

        Scanner scanner = new Scanner(System.in);
        int select = -1;

        do{
            System.out.print("\n*****嗖嗖移动用户菜单*****\n" +
                    "1.本月账单查询\n" +
                    "2.套餐余量查询\n" +
                    "3.打印消费详单\n" +
                    "4.套餐变更\n" +
                    "5.办理退网\n" +
                    "请选择(输入1-5选择功能，其他键返回上一级)：");
            try {
                select = scanner.nextInt();
            }
            catch(InputMismatchException e){
                scanner = new Scanner(System.in);
                System.out.println("请输入正确的数字");
            }

            switch (select)
            {
                case 1:
                    cardUtil.showAmountDetail(card);
                    break;

                case 2:
                    cardUtil.showRemainDetail(card);
                    break;

                case 3:
                    cardUtil.printConsumInfo(card);
                    break;

                case 4:
                    System.out.println("******套餐变更******");
                    String numIndex;
                    do{
                        System.out.print("1.话痨套餐 2.网虫套餐 3.超人套餐 请选择(序号)：");
                        numIndex = scanner.next();
                    } while(!numIndex.equals("1") && !numIndex.equals("2") && !numIndex.equals("3"));

                    cardUtil.changingPack(card, numIndex);

                    break;

                case 5:
                    cardUtil.delCard(card);
                    return;

                default:
                    return;
            }
        } while(true);
    }
}
