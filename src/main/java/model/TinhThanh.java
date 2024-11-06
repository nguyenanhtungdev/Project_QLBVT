package model;

public enum TinhThanh {
    AN_GIANG, BA_RIA_VUNG_TAU, BAC_GIANG, BAC_KAN, BAC_LIEU,
    BAC_NINH, BEN_TRE, BINH_DINH, BINH_DUONG, BINH_PHUOC,
    BINH_THUAN, CA_MAU, CAN_THO, CAO_BANG, DA_NANG,
    DAK_LAK, DAK_NONG, DIEN_BIEN, DONG_NAI, DONG_THAP,
    GIA_LAI, HA_GIANG, HA_NAM, HA_NOI, HA_TINH,
    HAI_DUONG, HAI_PHONG, HAU_GIANG, HOA_BINH, HUNG_YEN,
    KHANH_HOA, KIEN_GIANG, KON_TUM, LAI_CHAU, LAM_DONG,
    LANG_SON, LAO_CAI, LONG_AN, NAM_DINH, NGHE_AN,
    NINH_BINH, NINH_THUAN, PHU_THO, PHU_YEN, QUANG_BINH,
    QUANG_NAM, QUANG_NGAI, QUANG_NINH, QUANG_TRI, SOC_TRANG,
    SON_LA, TAY_NINH, THAI_BINH, THAI_NGUYEN, THANH_HOA,
    THUA_THIEN_HUE, TIEN_GIANG, TP_HO_CHI_MINH,TRA_VINH, TUYEN_QUANG,
    VINH_LONG, VINH_PHUC, YEN_BAI;

    @Override
    public String toString() {
        switch(this) {
            case AN_GIANG: return "An Giang";
            case BA_RIA_VUNG_TAU: return "Bà Rịa - Vũng Tàu";
            case BAC_GIANG: return "Bắc Giang";
            case BAC_KAN: return "Bắc Kạn";
            case BAC_LIEU: return "Bạc Liêu";
            case BAC_NINH: return "Bắc Ninh";
            case BEN_TRE: return "Bến Tre";
            case BINH_DINH: return "Bình Định";
            case BINH_DUONG: return "Bình Dương";
            case BINH_PHUOC: return "Bình Phước";
            case BINH_THUAN: return "Bình Thuận";
            case CA_MAU: return "Cà Mau";
            case CAN_THO: return "Cần Thơ";
            case CAO_BANG: return "Cao Bằng";
            case DA_NANG: return "Đà Nẵng";
            case DAK_LAK: return "Đắk Lắk";
            case DAK_NONG: return "Đắk Nông";
            case DIEN_BIEN: return "Điện Biên";
            case DONG_NAI: return "Đồng Nai";
            case DONG_THAP: return "Đồng Tháp";
            case GIA_LAI: return "Gia Lai";
            case HA_GIANG: return "Hà Giang";
            case HA_NAM: return "Hà Nam";
            case HA_NOI: return "Hà Nội";
            case HA_TINH: return "Hà Tĩnh";
            case HAI_DUONG: return "Hải Dương";
            case HAI_PHONG: return "Hải Phòng";
            case HAU_GIANG: return "Hậu Giang";
            case HOA_BINH: return "Hòa Bình";
            case HUNG_YEN: return "Hưng Yên";
            case KHANH_HOA: return "Khánh Hòa";
            case KIEN_GIANG: return "Kiên Giang";
            case KON_TUM: return "Kon Tum";
            case LAI_CHAU: return "Lai Châu";
            case LAM_DONG: return "Lâm Đồng";
            case LANG_SON: return "Lạng Sơn";
            case LAO_CAI: return "Lào Cai";
            case LONG_AN: return "Long An";
            case NAM_DINH: return "Nam Định";
            case NGHE_AN: return "Nghệ An";
            case NINH_BINH: return "Ninh Bình";
            case NINH_THUAN: return "Ninh Thuận";
            case PHU_THO: return "Phú Thọ";
            case PHU_YEN: return "Phú Yên";
            case QUANG_BINH: return "Quảng Bình";
            case QUANG_NAM: return "Quảng Nam";
            case QUANG_NGAI: return "Quảng Ngãi";
            case QUANG_NINH: return "Quảng Ninh";
            case QUANG_TRI: return "Quảng Trị";
            case SOC_TRANG: return "Sóc Trăng";
            case SON_LA: return "Sơn La";
            case TAY_NINH: return "Tây Ninh";
            case THAI_BINH: return "Thái Bình";
            case THAI_NGUYEN: return "Thái Nguyên";
            case THANH_HOA: return "Thanh Hóa";
            case THUA_THIEN_HUE: return "Thừa Thiên Huế";
            case TIEN_GIANG: return "Tiền Giang";
            case TP_HO_CHI_MINH: return "TP Hồ Chí Minh";
            case TRA_VINH: return "Trà Vinh";
            case TUYEN_QUANG: return "Tuyên Quang";
            case VINH_LONG: return "Vĩnh Long";
            case VINH_PHUC: return "Vĩnh Phúc";
            case YEN_BAI: return "Yên Bái";
            default: return super.toString();
        }
    }
}
