package randomhau;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ThangVo
 */
public class ThuatToan {

    //Khai báo một bàn cờ là 1 ma trận(mảng 2 chiều).
    private int[][] banco;
    //Khai báo kích thước của bàn cờ
    private int kichthuocbanco;
    //Khai báo một mảng chứa các quân hậu được thêm vào.
    private ArrayList<QuanHau> danhsachquanhau;
    //Khai báo tổng số quân hậu sẽ xung đột với nhau.
    private int sohauxungdotnhau;
    //Khai báo màng để chứa con hậu cần di chuyển và chứa điểm di chuyển. dung quan tam
    private QuanHau[] vitrihaudichuyen;

    //Khởi tạo bàn cờ
    public void khoiTaoBanCo(int n) {
        //Set kích thước bàn cờ với n là tham số được truyền vào dùng phương thức setKichthuocbanco();
        setKichthuocbanco(n);
        //Khởi tạo bàn cờ với kích thước vừa được set ở trên bằng phương thức getKichthuocbanco();
        banco = new int[getKichthuocbanco()][getKichthuocbanco()];

    }

    //Khởi tạo vị trí quân hậu vào bàn cờ
    public void khoiTaoViTriQuanHau() {
        //Khai báo 1 đối tượng quân hậu
        QuanHau quanHau;
        //Khởi tạo danh sách quân hậu là một arraylist
        danhsachquanhau = new ArrayList<>();
        //Hàm random số
        Random random = new Random();
        //Chạy dòng lặp while để random quân hậu theo kích thước bàn cờ
        int count = 0;
        while (danhsachquanhau.size() < getKichthuocbanco()) {
            // Khởi tạo 1 quân hậu với vị trí random

            quanHau = new QuanHau(random.nextInt(getKichthuocbanco()), count);
            //Kiểm tra vị tri quân hậu có trùng nhau không (cho trường hợp random trùng hàng cột: chưa dùng)
            if (!kiemTraViTriQuanHau(quanHau)) {
                //Không trùng thêm vào danh sách quân hậu
                danhsachquanhau.add(quanHau);
                count++;

            }

        }
        //Chạy dòng lặp for gán vị trí hậu hiện tại vào bàn cờ và được đánh số là 1
        for (int j = 0; j < danhsachquanhau.size(); j++) {
            //1-0
            //2-1    
            //2-2
            //2-3
            banco[danhsachquanhau.get(j).getVitridong()][danhsachquanhau.get(j).getVitricot()] = 1;

            //Set tổng xung đột hiện tại của trạng thái bàn cờ ban đầu.
            setSohauxungdotnhau(tongXungDotHienTai(danhsachquanhau));
        }

    }

    //Tính xung đột của toàn bộ bàn cờ (trạng thái ban đầu).
    public int tongXungDotHienTai(ArrayList<QuanHau> quanhau) {
        //Biến kết quả
        int sum = 0;
        //CHạy vòng for thứ nhất duyệt con hậu i
        for (int i = 0; i < quanhau.size(); i++) {
            //Chạy vòng for thứ hai duyệt con hậu j(sau con hậu i). 
            for (int j = i + 1; j < quanhau.size(); j++) {
                //Chạy kiểm tra điều kiện 2 con hậu có xung đột nhau không
                if (quanhau.get(i).kiemTraXungDot2QuanHau(quanhau.get(j))) {
                    //Xung đột tăng biến điếm
                    sum++;
                }
            }
        }
        //Trả kết quả
        return sum;
    }

//Sắp xếp danh sách quân hậu theo chiều giam dần xung đôt
    public ArrayList<QuanHau> sapXepGiamDanTheoXungDot() {
        //Chạy vòng for object duyệt toàn bộ quân hậu
        for (QuanHau queen : getDanhsachquanhau()) {
            //Tính xung đột từng quân hậu  và set xung đột cho quân hậu vừa tính
            queen.setSoxungdot(soLuongXungDotMotQuanHau(queen));
        }

        //Lấy lại danh sách quân hậu hiện tại
        ArrayList<QuanHau> tempQH = getDanhsachquanhau();
        //Khai báo 1 quân hậu
        QuanHau tempQueen;
        //Chạy vòng for thứ nhất duyệt con hậu i
        for (int i = 0; i < getKichthuocbanco(); i++) {
            //Chạy vòng for thứ hai duyệt con hậu j (sau con hậu i).
            for (int j = i; j < getKichthuocbanco(); j++) {
                //Kiểm tra hậu i có bé hơn hoặc bằng con j hay không
                if (tempQH.get(i).getSoxungdot() <= tempQH.get(j).getSoxungdot()) {
                    //Lấy vị trí co hậu i gán vào quân hậu vừa khai báo.(Lưu lại vị trí con hậu xung đột bé hơn).
                    tempQueen = tempQH.get(i);
                    //Set lại con hậu j vào vị trí của i và đưa vào danh sách.
                    tempQH.set(i, tempQH.get(j));
                    //Set lại con hậu i vào danh sách.
                    tempQH.set(j, tempQueen);
                }

            }
        }
        //Trả về danh sách quân hậu theo chiều giảm dần quân hậu.
        return tempQH;
    }

    //Tìm vị trí cần di chuyển hậu
    public QuanHau[] timViTriDiChuyen() {
        //Lưu lại xung đột của trạng thái bàn cờ mới so với trạng thái bàn cờ ban đầu.
        int tongxungdotmoi = 0;
        //Lưu lại vị trí dòng và cột của con hậu cần xét.
        int tempdong, tempcot;
        //Khai báo mảng đối tượng lưu vị trí hậu và bước di chuyển.
        QuanHau[] tempArrayQueen = null;
        //Lấy toàn bộ danh sách quân hậu đã xắp theo chiều giảm dần xung đột
        ArrayList<QuanHau> list_hautmp = sapXepGiamDanTheoXungDot();
        //Chạy vòng for object
        for (QuanHau queen : list_hautmp) {
            //Lưu vị trí dòng
            tempdong = queen.getVitridong();
            //Lưu vị trí cột
            tempcot = queen.getVitricot();
            //Set lại vị trí con hậu hiện tại trên bàn cờ bằng 0.
            banco[queen.getVitridong()][queen.getVitricot()] = 0;
            //Chạy vòng for thứ nhất là cot của bàn cờ.
            for (int j = queen.getVitricot(); j < queen.getVitricot()+1; j++) {
                //Chạy vòng for thứ hai là cột cùa bàn cờ.
                for (int i = 0; i < banco.length; i++) {
                    //Kiểm tra vị trí bàn cờ i và j nếu bằng 0
                    if (banco[i][j] == 0) {
                        //Gán vị trí đó bằng 1.
                        banco[i][j] = 1;
                        //Sét lại vị trí dòng và cột mới cho con hậu.
                        queen.setVitridong(i);
                        queen.setVitricot(j);
                        //Tính lại tổng xung đột trong trang thái bàn cờ mới.
                        tongxungdotmoi = tongXungDotHienTai(list_hautmp);
                        //Trả lại vị trí dòng và cột ban đầu cho con hậu.
                        queen.setVitridong(tempdong);
                        queen.setVitricot(tempcot);
                        //Kiểm tra tồng xung đột của trạng thái bàn cờ mới so với trạng thái bàn cờ ban đầu.
                        if (tongxungdotmoi < sohauxungdotnhau) {
                            //Set lại tổng xung đột của trạng thái bàn cờ.
                            setSohauxungdotnhau(tongxungdotmoi);
                            //Khởi tạo mảng đối tượng gồm 2 phần tử
                            tempArrayQueen = new QuanHau[2];
                            // [0]: Gán vị trí con hậu.
                            tempArrayQueen[0] = new QuanHau(queen.getVitridong(), queen.getVitricot());
                            //[1]:Gán vị trí di chuyển hậu.
                            tempArrayQueen[1] = new QuanHau(i, j);
                        }
                        //Không xảy ra if thì trả về bàn cờ cũ.
                        banco[i][j] = 0;
                    }
                }
            }
            //Gán vị trí của quân hậu bằng 1.
            banco[queen.getVitridong()][queen.getVitricot()] = 1;
            //Kiểm tra nếu mảng có giá trị thì dừng.
            if (tempArrayQueen != null) {
                break;
            }
        }
        //Trả về kết quả gồm vị trí hậu và vị trí di chuyển.

        return tempArrayQueen;

    }

    //Thực hiện di chuyển con hậu.
    public void diChuyenQuanHau(QuanHau[] queens) {
        //Gán vị trí hậu hiện tại bằng 0.
        banco[queens[0].getVitridong()][queens[0].getVitricot()] = 0;
        //Gán vị trí di chuyển đến cho quân hậu bằng 1.
        banco[queens[1].getVitridong()][queens[1].getVitricot()] = 1;
        //Chạy vòng for object duyệt danh sách quân hậu.
        for (QuanHau queen : danhsachquanhau) {
            //Kiểm tra trong danh sách quân hậu xem con nào trùng vị trí với con cần di chuyển.
            if (queen.getVitridong() == queens[0].getVitridong() && queen.getVitricot() == queens[0].getVitricot()) {
                //Gám lại vị trí dòng cột bằng vị trí di chuyển cho quân hậu trùng.
                queen.setVitridong(queens[1].getVitridong());
                queen.setVitricot(queens[1].getVitricot());
                break;
            }
        }
    }

    //So sánh vị trí của các quân hậu trong danh sách trước khi thêm vào
    public boolean kiemTraViTriQuanHau(QuanHau quanHau) {
        //Chạy dòng lặp for duyệt các quân hậu trong danh sách với quân hậu được truyền vào đễ kiểm tra
        for (int i = 0; i < danhsachquanhau.size(); i++) {
            //Nếu quân hậu cần kiểm tra có hàng và cột trùng với các con hậu trong danh sách
            if (danhsachquanhau.get(i).getVitridong() == quanHau.getVitridong() && danhsachquanhau.get(i).getVitricot() == quanHau.getVitricot()) {
                //Trả về trùng
                return true;
            }
        }
        //Ngược lại trả về không trùng
        return false;

    }

    //Tính số lượng xung đột của 1 quân hậu
    public int soLuongXungDotMotQuanHau(QuanHau quanHau) {
        //Khai báo biến dòng cột lưu lại vị trí dòng cột hiện tại của quân hậu
        int dong, cot, tong = 0;

        //Tính xung đột phía trên dòng hiện tại
        //Gắn vị trí dòng con hậu hiện tại
        dong = quanHau.getVitridong();
        //Chạy dòng while cho dòng hiện tại(--dong: giảm dòng)
        while (--dong >= 0) {
            //Kiểm tra vị trí con hậu tại dòng hiện tại có trùng với con nào không
            if (banco[dong][quanHau.getVitricot()] == 1) {
                //Trùng tăng biến đếm 
                tong++;
                break;
            }
        }
        //Tính xung đột phía dưới dòng hiện tại
        //Gắn vị trí dòng con hậu hiện tại
        dong = quanHau.getVitridong();
        //Chạy dòng while cho dòng hiện tại (++dong: tăng dòng)
        while (++dong < getKichthuocbanco()) {
            //Kiểm tra vị trí con hậu tại dòng hiện tại có trùng với con nào không
            if (banco[dong][quanHau.getVitricot()] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }
        //tính xung đột bên trái dòng hiện tại
        //Gắn vị trí cột con hậu hiện tại
        cot = quanHau.getVitricot();
        //Chạy dòng while cho cột hiện tại (--cot: giảm cột )
        while (--cot >= 0) {
            //Kiểm tra vị trí con hậu tại cột hiện tại có trùng với con nào không
            if (banco[quanHau.getVitridong()][cot] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }
        //tính xung đột bên phải dòng hiện tại
        //Gắn vị trí cột con hậu hiện tại
        cot = quanHau.getVitricot();
        //Chạy dòng while cho cột hiện tại (++cot: tăng cột)
        while (++cot < getKichthuocbanco()) {
            //Kiểm tra vị trí con hậu tại cột hiện tại có trùng với con nào không
            if (banco[quanHau.getVitridong()][cot] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }

        //tính xung đột chéo trên bên trái dòng cột hiện tại
        //Gắn vị trí dòng cột con hậu hiện tại
        dong = quanHau.getVitridong();
        cot = quanHau.getVitricot();
        //Chạy dòng while cho dòng cột hiện tại (--dong, --cot: giảm dòng, cột )
        while (--dong >= 0 && --cot >= 0) {
            //Kiểm tra vị trí con hậu tại dòng cột hiện tại có trùng với con nào không
            if (banco[dong][cot] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }

        //tính xung đột chéo trên bên phải dòng cột hiện tại
        //Gắn vị trí dòng cột con hậu hiện tại
        dong = quanHau.getVitridong();
        cot = quanHau.getVitricot();

        //Chạy dòng while cho dòng cột hiện tại (--dong, ++cot: giảm dòng, tăng cột)
        while (--dong >= 0 && ++cot < getKichthuocbanco()) {
            //Kiểm tra vị trí con hậu tại dòng cột hiện tại có trùng với con nào không
            if (banco[dong][cot] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }

        //tính xung đột chéo dưới bên trái dòng cột hiện tại
        //Gắn vị trí dòng cột con hậu hiện tại
        dong = quanHau.getVitridong();
        cot = quanHau.getVitricot();
        //Chạy dòng while cho dòng cột hiện tại (++dong, --cot: tăng dòng, giảm cột )
        while (++dong < getKichthuocbanco() && --cot >= 0) {
            //Kiểm tra vị trí con hậu tại dòng cột hiện tại có trùng với con nào không
            if (banco[dong][cot] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }

        //tính xung đột chéo dưới bên phải dòng cột hiện tại
        //Gắn vị trí dòng cột con hậu hiện tại
        dong = quanHau.getVitridong();
        cot = quanHau.getVitricot();
        //Chạy dòng while cho dòng cột hiện tại (++dong, ++cot: tăng dòng, tăng cột )
        while (++dong < getKichthuocbanco() && ++cot < getKichthuocbanco()) {
            //Kiểm tra vị trí con hậu tại dòng cột hiện tại có trùng với con nào không
            if (banco[dong][cot] == 1) {
                //Trùng tăng biến đếm
                tong++;
                break;
            }
        }

        return tong;
    }

    @Override
    public String toString() {
        //Khai báo chuỗi cần trả về
        String b = "";
        //Chạy dòng lặp for thứ nhất là dòng của bàn cờ
        for (int i = 0; i < banco.length; i++) {
            //Chạy dòng lặp for thứ hai là cột của bàn cờ
            for (int j = 0; j < banco.length; j++) {
                //Nếu dòng và cột bằng không
                if (banco[i][j] == 0) {
                    //Gán là dấu sao
                    b += " * ";
                } else {
                    //Ngược lại gán chữ Q
                    b += " Q ";
                }

            }
            b += "\n";
        }
        //Trả về chuỗi b
        return b;

    }

    //Hiển thị bàn cờ
    public void inBanCo() {
        //Chạy dòng lặp for thứ nhất là dòng của bàn cờ
        for (int i = 0; i < banco.length; i++) {
            //Chạy dòng lặp for thứ hai là cột của bàn cờ
            for (int j = 0; j < banco.length; j++) {
                System.out.print(banco[i][j] + "  ");
            }

            System.out.println("\n");
        }
    }

    public int[][] getBanco() {
        return banco;
    }

    public void setBanco(int[][] banco) {
        this.banco = banco;
    }

    public int getKichthuocbanco() {
        return kichthuocbanco;
    }

    public void setKichthuocbanco(int kichthuocbanco) {
        this.kichthuocbanco = kichthuocbanco;
    }

    public ArrayList<QuanHau> getDanhsachquanhau() {
        return danhsachquanhau;
    }

    public void setDanhsachquanhau(ArrayList<QuanHau> danhsachquanhau) {
        this.danhsachquanhau = danhsachquanhau;
    }

    public int getSohauxungdotnhau() {
        return sohauxungdotnhau;
    }

    public void setSohauxungdotnhau(int sohauxungdotnhau) {
        this.sohauxungdotnhau = sohauxungdotnhau;
    }

    public QuanHau[] getVitrihaudichuyen() {
        return vitrihaudichuyen;
    }

    public void setVitrihaudichuyen(QuanHau[] vitrihaudichuyen) {
        this.vitrihaudichuyen = vitrihaudichuyen;
    }

}
