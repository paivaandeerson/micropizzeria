import axios from 'axios';
import { Payment } from '../../domain/models/payment';

export class AdyenService {
  async processPayment(payment: Payment): Promise<any> {
    const response = await axios.post('https://your-adyen-endpoint/api/initiatePayment', {
      paymentMethod: {
        type: "scheme",
        number: payment.cardNumber,
        expiryMonth: payment.expiryMonth,
        expiryYear: payment.expiryYear,
        holderName: payment.cardholderName,
        cvc: payment.cardSecurityCode,
      }
    });

    return response.data;
  }
}
