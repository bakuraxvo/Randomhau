package randomhau;

/**
 *
 * @author ThangVo
 */
public class QuanHau {
    private int vitridong;
    private int vitricot;
    private int soxungdot;

    public QuanHau(int vitridong, int vitricot) {
        this.vitridong = vitridong;
        this.vitricot = vitricot;
    }

    public int getVitridong() {
        return vitridong;
    }

    public void setVitridong(int vitridong) {
        this.vitridong = vitridong;
    }

    public int getVitricot() {
        return vitricot;
    }

    public void setVitricot(int vitricot) {
        this.vitricot = vitricot;
    }

    public int getSoxungdot() {
        return soxungdot;
    }

    public void setSoxungdot(int soxungdot) {
        this.soxungdot = soxungdot;
    }
    
    
      public boolean kiemTraXungDot2QuanHau(QuanHau quanHau) {
        //Chạy dòng lặp for duyệt các quân hậu trong danh sách với quân hậu được truyền vào đễ kiểm tra
       
            //Nếu quân hậu cần kiểm tra có hàng và cột trùng với các con hậu trong danh sách
            //Con 1
            int i=getVitridong();
            int j=getVitricot();
            //con 2 
            int k=quanHau.getVitricot();
            int l=quanHau.getVitridong();
            if (i == l || j == k) {
                //Trả về trùng
                return true;
            }
            //Tính đuòng cheo
            else if(Math.abs(j-k) == Math.abs(i-l))
            return true;
        return false;
      
       
    }
      
   
    
}
