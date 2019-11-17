package com.sosoMobie;

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
                    String card = cardUtil.singnedIn();
                    if (card != null) {
                        subMenu1(cardUtil, card);
                    }
                    break;

                case 2:
                    cardUtil.addCard();
                    break;

                case 3:
                    cardUtil.useSoso();
                    break;

                case 4:
                    cardUtil.chargeMoney();
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
                    cardUtil.changingPack(card);
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
