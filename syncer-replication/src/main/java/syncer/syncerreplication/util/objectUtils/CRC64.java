/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache LicenseL, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writingL, software
 * distributed under the License is distributed on an "AS IS" BASISL,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KINDL, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package syncer.syncerreplication.util.objectUtils;

/**
 * @author Leon Chen
 * @since 2.5.0
 */
public class CRC64 {
    public static final long[] LOOKUP_TABLE = {
            0x0000000000000000L, 0x7AD870C830358979L, 0xF5B0E190606B12F2L, 0x8F689158505E9B8BL,
            0xC038E5739841B68FL, 0xBAE095BBA8743FF6L, 0x358804E3F82AA47DL, 0x4F50742BC81F2D04L,
            0xAB28ECB46814FE75L, 0xD1F09C7C5821770CL, 0x5E980D24087FEC87L, 0x24407DEC384A65FEL,
            0x6B1009C7F05548FAL, 0x11C8790FC060C183L, 0x9EA0E857903E5A08L, 0xE478989FA00BD371L,
            0x7D08FF3B88BE6F81L, 0x07D08FF3B88BE6F8L, 0x88B81EABE8D57D73L, 0xF2606E63D8E0F40AL,
            0xBD301A4810FFD90EL, 0xC7E86A8020CA5077L, 0x4880FBD87094CBFCL, 0x32588B1040A14285L,
            0xD620138FE0AA91F4L, 0xACF86347D09F188DL, 0x2390F21F80C18306L, 0x594882D7B0F40A7FL,
            0x1618F6FC78EB277BL, 0x6CC0863448DEAE02L, 0xE3A8176C18803589L, 0x997067A428B5BCF0L,
            0xFA11FE77117CDF02L, 0x80C98EBF2149567BL, 0x0FA11FE77117CDF0L, 0x75796F2F41224489L,
            0x3A291B04893D698DL, 0x40F16BCCB908E0F4L, 0xCF99FA94E9567B7FL, 0xB5418A5CD963F206L,
            0x513912C379682177L, 0x2BE1620B495DA80EL, 0xA489F35319033385L, 0xDE51839B2936BAFCL,
            0x9101F7B0E12997F8L, 0xEBD98778D11C1E81L, 0x64B116208142850AL, 0x1E6966E8B1770C73L,
            0x8719014C99C2B083L, 0xFDC17184A9F739FAL, 0x72A9E0DCF9A9A271L, 0x08719014C99C2B08L,
            0x4721E43F0183060CL, 0x3DF994F731B68F75L, 0xB29105AF61E814FEL, 0xC849756751DD9D87L,
            0x2C31EDF8F1D64EF6L, 0x56E99D30C1E3C78FL, 0xD9810C6891BD5C04L, 0xA3597CA0A188D57DL,
            0xEC09088B6997F879L, 0x96D1784359A27100L, 0x19B9E91B09FCEA8BL, 0x636199D339C963F2L,
            0xDF7ADABD7A6E2D6FL, 0xA5A2AA754A5BA416L, 0x2ACA3B2D1A053F9DL, 0x50124BE52A30B6E4L,
            0x1F423FCEE22F9BE0L, 0x659A4F06D21A1299L, 0xEAF2DE5E82448912L, 0x902AAE96B271006BL,
            0x74523609127AD31AL, 0x0E8A46C1224F5A63L, 0x81E2D7997211C1E8L, 0xFB3AA75142244891L,
            0xB46AD37A8A3B6595L, 0xCEB2A3B2BA0EECECL, 0x41DA32EAEA507767L, 0x3B024222DA65FE1EL,
            0xA2722586F2D042EEL, 0xD8AA554EC2E5CB97L, 0x57C2C41692BB501CL, 0x2D1AB4DEA28ED965L,
            0x624AC0F56A91F461L, 0x1892B03D5AA47D18L, 0x97FA21650AFAE693L, 0xED2251AD3ACF6FEAL,
            0x095AC9329AC4BC9BL, 0x7382B9FAAAF135E2L, 0xFCEA28A2FAAFAE69L, 0x8632586ACA9A2710L,
            0xC9622C4102850A14L, 0xB3BA5C8932B0836DL, 0x3CD2CDD162EE18E6L, 0x460ABD1952DB919FL,
            0x256B24CA6B12F26DL, 0x5FB354025B277B14L, 0xD0DBC55A0B79E09FL, 0xAA03B5923B4C69E6L,
            0xE553C1B9F35344E2L, 0x9F8BB171C366CD9BL, 0x10E3202993385610L, 0x6A3B50E1A30DDF69L,
            0x8E43C87E03060C18L, 0xF49BB8B633338561L, 0x7BF329EE636D1EEAL, 0x012B592653589793L,
            0x4E7B2D0D9B47BA97L, 0x34A35DC5AB7233EEL, 0xBBCBCC9DFB2CA865L, 0xC113BC55CB19211CL,
            0x5863DBF1E3AC9DECL, 0x22BBAB39D3991495L, 0xADD33A6183C78F1EL, 0xD70B4AA9B3F20667L,
            0x985B3E827BED2B63L, 0xE2834E4A4BD8A21AL, 0x6DEBDF121B863991L, 0x1733AFDA2BB3B0E8L,
            0xF34B37458BB86399L, 0x8993478DBB8DEAE0L, 0x06FBD6D5EBD3716BL, 0x7C23A61DDBE6F812L,
            0x3373D23613F9D516L, 0x49ABA2FE23CC5C6FL, 0xC6C333A67392C7E4L, 0xBC1B436E43A74E9DL,
            0x95AC9329AC4BC9B5L, 0xEF74E3E19C7E40CCL, 0x601C72B9CC20DB47L, 0x1AC40271FC15523EL,
            0x5594765A340A7F3AL, 0x2F4C0692043FF643L, 0xA02497CA54616DC8L, 0xDAFCE7026454E4B1L,
            0x3E847F9DC45F37C0L, 0x445C0F55F46ABEB9L, 0xCB349E0DA4342532L, 0xB1ECEEC59401AC4BL,
            0xFEBC9AEE5C1E814FL, 0x8464EA266C2B0836L, 0x0B0C7B7E3C7593BDL, 0x71D40BB60C401AC4L,
            0xE8A46C1224F5A634L, 0x927C1CDA14C02F4DL, 0x1D148D82449EB4C6L, 0x67CCFD4A74AB3DBFL,
            0x289C8961BCB410BBL, 0x5244F9A98C8199C2L, 0xDD2C68F1DCDF0249L, 0xA7F41839ECEA8B30L,
            0x438C80A64CE15841L, 0x3954F06E7CD4D138L, 0xB63C61362C8A4AB3L, 0xCCE411FE1CBFC3CAL,
            0x83B465D5D4A0EECEL, 0xF96C151DE49567B7L, 0x76048445B4CBFC3CL, 0x0CDCF48D84FE7545L,
            0x6FBD6D5EBD3716B7L, 0x15651D968D029FCEL, 0x9A0D8CCEDD5C0445L, 0xE0D5FC06ED698D3CL,
            0xAF85882D2576A038L, 0xD55DF8E515432941L, 0x5A3569BD451DB2CAL, 0x20ED197575283BB3L,
            0xC49581EAD523E8C2L, 0xBE4DF122E51661BBL, 0x3125607AB548FA30L, 0x4BFD10B2857D7349L,
            0x04AD64994D625E4DL, 0x7E7514517D57D734L, 0xF11D85092D094CBFL, 0x8BC5F5C11D3CC5C6L,
            0x12B5926535897936L, 0x686DE2AD05BCF04FL, 0xE70573F555E26BC4L, 0x9DDD033D65D7E2BDL,
            0xD28D7716ADC8CFB9L, 0xA85507DE9DFD46C0L, 0x273D9686CDA3DD4BL, 0x5DE5E64EFD965432L,
            0xB99D7ED15D9D8743L, 0xC3450E196DA80E3AL, 0x4C2D9F413DF695B1L, 0x36F5EF890DC31CC8L,
            0x79A59BA2C5DC31CCL, 0x037DEB6AF5E9B8B5L, 0x8C157A32A5B7233EL, 0xF6CD0AFA9582AA47L,
            0x4AD64994D625E4DAL, 0x300E395CE6106DA3L, 0xBF66A804B64EF628L, 0xC5BED8CC867B7F51L,
            0x8AEEACE74E645255L, 0xF036DC2F7E51DB2CL, 0x7F5E4D772E0F40A7L, 0x05863DBF1E3AC9DEL,
            0xE1FEA520BE311AAFL, 0x9B26D5E88E0493D6L, 0x144E44B0DE5A085DL, 0x6E963478EE6F8124L,
            0x21C640532670AC20L, 0x5B1E309B16452559L, 0xD476A1C3461BBED2L, 0xAEAED10B762E37ABL,
            0x37DEB6AF5E9B8B5BL, 0x4D06C6676EAE0222L, 0xC26E573F3EF099A9L, 0xB8B627F70EC510D0L,
            0xF7E653DCC6DA3DD4L, 0x8D3E2314F6EFB4ADL, 0x0256B24CA6B12F26L, 0x788EC2849684A65FL,
            0x9CF65A1B368F752EL, 0xE62E2AD306BAFC57L, 0x6946BB8B56E467DCL, 0x139ECB4366D1EEA5L,
            0x5CCEBF68AECEC3A1L, 0x2616CFA09EFB4AD8L, 0xA97E5EF8CEA5D153L, 0xD3A62E30FE90582AL,
            0xB0C7B7E3C7593BD8L, 0xCA1FC72BF76CB2A1L, 0x45775673A732292AL, 0x3FAF26BB9707A053L,
            0x70FF52905F188D57L, 0x0A2722586F2D042EL, 0x854FB3003F739FA5L, 0xFF97C3C80F4616DCL,
            0x1BEF5B57AF4DC5ADL, 0x61372B9F9F784CD4L, 0xEE5FBAC7CF26D75FL, 0x9487CA0FFF135E26L,
            0xDBD7BE24370C7322L, 0xA10FCEEC0739FA5BL, 0x2E675FB4576761D0L, 0x54BF2F7C6752E8A9L,
            0xCDCF48D84FE75459L, 0xB71738107FD2DD20L, 0x387FA9482F8C46ABL, 0x42A7D9801FB9CFD2L,
            0x0DF7ADABD7A6E2D6L, 0x772FDD63E7936BAFL, 0xF8474C3BB7CDF024L, 0x829F3CF387F8795DL,
            0x66E7A46C27F3AA2CL, 0x1C3FD4A417C62355L, 0x935745FC4798B8DEL, 0xE98F353477AD31A7L,
            0xA6DF411FBFB21CA3L, 0xDC0731D78F8795DAL, 0x536FA08FDFD90E51L, 0x29B7D047EFEC8728L,
    };

    public static long crc64(byte[] bytes) {
        return crc64(bytes, 0, bytes.length);
    }

    public static long crc64(byte[] bytes, long sum) {
        return crc64(bytes, 0, bytes.length, sum);
    }

    public static long crc64(byte[] bytes, int start, int length) {
        return crc64(bytes, start, length, 0L);
    }

    public static long crc64(byte[] bytes, int start, int length, long sum) {
        for (int i = start; i < start + length; i++) {
            sum = (sum >>> 8) ^ LOOKUP_TABLE[((int) sum ^ bytes[i]) & 0xFF];
        }
        return sum;
    }

    public static byte[] longToByteArray(long value) {
        return new byte[]{
                (byte) value,
                (byte) (value >> 8),
                (byte) (value >> 16),
                (byte) (value >> 24),
                (byte) (value >> 32),
                (byte) (value >> 40),
                (byte) (value >> 48),
                (byte) (value >> 56),
        };
    }
}
