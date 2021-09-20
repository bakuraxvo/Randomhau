package randomhau;

import java.util.ArrayList;

/**
 *
 * @author ThangVo
 */
public class Main {

    public static void main(String[] args) {
        /*
        ThuatToan thuatToan = new ThuatToan();
        thuatToan.khoiTaoBanCo(4);
        thuatToan.khoiTaoViTriQuanHau();
        System.out.println("Bàn cờ ban đầu:");
        thuatToan.inBanCo();
        
        System.out.println("-----------------");

        ArrayList<QuanHau> sss = thuatToan.getDanhsachquanhau();

        System.out.println("Tổng số xung đột: " + thuatToan.tongXungDotHienTai(sss));
        System.out.println("-----------------");
        for (int i = 0; i < sss.size(); i++) {

            System.out.println("Số xung đột của quân hậu " + "[" + sss.get(i).getVitridong() + "-" + sss.get(i).getVitricot() + "]" + ": " + thuatToan.soLuongXungDotMotQuanHau(sss.get(i)));

        }
        System.out.println("-------------Danh sách quân hậu sau khi sắp theo tăng dần xung đột--------------");
        ArrayList<QuanHau> ss = thuatToan.sapXepGiamDanTheoXungDot();

        for (QuanHau q : ss) {
            System.out.print("[" + q.getVitridong() + "-" + q.getVitricot() + "]" + " ");
        }
        System.out.println();
         */

 /*
        ThuatToan thuatToan = new ThuatToan();
        thuatToan.khoiTaoBanCo(4);
        thuatToan.khoiTaoViTriQuanHau();
        System.out.println("Bàn cờ ban đầu:");
        thuatToan.inBanCo();
       
        System.out.println("-----------------");

        ArrayList<QuanHau> sss = thuatToan.getDanhsachquanhau();

        System.out.println("Tổng số xung đột: " + thuatToan.tongXungDotHienTai(sss));
        System.out.println("-----------------");
        for (int i = 0; i < sss.size(); i++) {

            System.out.println("Số xung đột của quân hậu " + "[" + sss.get(i).getVitridong() + "-" + sss.get(i).getVitricot() + "]" + ": " + thuatToan.soLuongXungDotMotQuanHau(sss.get(i)));

        }
        
        System.out.println("-------------Danh sách quân hậu sau khi sắp theo tăng dần xung đột--------------");
        ArrayList<QuanHau> ss = thuatToan.sapXepGiamDanTheoXungDot();

        for (QuanHau q : ss) {
            System.out.print("[" + q.getVitridong() + "-" + q.getVitricot() + "]" + " ");
        }
        System.out.println();
        System.out.println("---------------Tìm hậu cần di chuyển và ô sẽ đi chuyển đến------------");
        QuanHau[] qh = thuatToan.timViTriDiChuyen();

        System.out.println("Con hậu cần di chuyển: " + qh[0].getVitridong() + "-" + qh[0].getVitricot());
        System.out.println("Vị trí con hậu sẽ đến: " + qh[1].getVitridong() + "-" + qh[1].getVitricot());
        System.out.println("-------------------");
        thuatToan.diChuyenQuanHau(qh);
        
        System.out.println(thuatToan.toString());
         ArrayList<QuanHau> ssss = thuatToan.getDanhsachquanhau();

        System.out.println("Tổng số xung đột: " + thuatToan.tongXungDotHienTai(ssss));
        System.out.println("-----------------");
        for (int i = 0; i < ssss.size(); i++) {

            System.out.println("Số xung đột của quân hậu " + "[" + ssss.get(i).getVitridong() + "-" + ssss.get(i).getVitricot() + "]" + ": " + thuatToan.soLuongXungDotMotQuanHau(sss.get(i)));

        }
        System.out.println("-------------Danh sách quân hậu sau khi sắp theo tăng dần xung đột--------------");
        ArrayList<QuanHau> ssa = thuatToan.sapXepGiamDanTheoXungDot();

        for (QuanHau q : ssa) {
            System.out.print("[" + q.getVitridong() + "-" + q.getVitricot() + "]" + " ");
        }
        System.out.println();
        
         */
        ThuatToan thuatToan = new ThuatToan();
        thuatToan.khoiTaoBanCo(4);
        thuatToan.khoiTaoViTriQuanHau();
        System.out.println("Trạng thái bàn cờ ban đầu");
        System.out.println("-------------------------");
        thuatToan.inBanCo();
        System.out.println("-------------------------");

        ArrayList<QuanHau> sss = thuatToan.getDanhsachquanhau();

        System.out.println("Tổng số xung đột: " + thuatToan.tongXungDotHienTai(sss));
        System.out.println("-----------------------------------");
        for (int i = 0; i < sss.size(); i++) {

            System.out.println("Số xung đột của quân hậu " + "[" + sss.get(i).getVitridong() + "-" + sss.get(i).getVitricot() + "]" + ": " + thuatToan.soLuongXungDotMotQuanHau(sss.get(i)));

        }
       
        int count=0;
        while (thuatToan.getSohauxungdotnhau() != 0) {
            //thuatToan.timViTriDiChuyen();
            thuatToan.setVitrihaudichuyen(thuatToan.timViTriDiChuyen());
            count++;
            
            if (thuatToan.getVitrihaudichuyen() == null) {
                thuatToan.khoiTaoBanCo(4);
                thuatToan.khoiTaoViTriQuanHau();
                System.out.println("-----Hét đường và random bàn cờ mới------");
                System.out.println("Random bàn cờ mới");
                thuatToan.inBanCo();
                System.out.println("-----------------");

                ArrayList<QuanHau> quanhaumoi = thuatToan.getDanhsachquanhau();

                System.out.println("Tổng số xung đột: " + thuatToan.tongXungDotHienTai(quanhaumoi));
                System.out.println("-----------------");
                for (int i = 0; i < quanhaumoi.size(); i++) {

                    System.out.println("Số xung đột của quân hậu " + "[" + quanhaumoi.get(i).getVitridong() + "-" + quanhaumoi.get(i).getVitricot() + "]" + ": " + thuatToan.soLuongXungDotMotQuanHau(quanhaumoi.get(i)));

                }
              
            } else {

                System.out.println("---------------Tìm hậu cần di chuyển và ô sẽ di chuyển đến------------");

                QuanHau[] qh = thuatToan.getVitrihaudichuyen();

                System.out.println("Con hậu cần di chuyển: " + qh[0].getVitridong() + "-" + qh[0].getVitricot());
                System.out.println("Vị trí con hậu sẽ đến: " + qh[1].getVitridong() + "-" + qh[1].getVitricot());
                thuatToan.diChuyenQuanHau(qh);
                System.out.println("---------------------");
                System.out.println("Trạng thái bàn cờ mới");
                System.out.println("---------------------");
                thuatToan.inBanCo();
                System.out.println("---------------------");

                ArrayList<QuanHau> quanhaumoi2 = thuatToan.getDanhsachquanhau();

                System.out.println("Tổng số xung đột: " + thuatToan.tongXungDotHienTai(quanhaumoi2));
                System.out.println("-----------------");
                for (int i = 0; i < quanhaumoi2.size(); i++) {

                    System.out.println("Số xung đột của quân hậu " + "[" + quanhaumoi2.get(i).getVitridong() + "-" + quanhaumoi2.get(i).getVitricot() + "]" + ": " + thuatToan.soLuongXungDotMotQuanHau(quanhaumoi2.get(i)));

                }
               

            }
        }
        System.out.println("------------------");
        
        thuatToan.inBanCo();
        System.out.println("Số lần lặp: "+count);

    }

}
