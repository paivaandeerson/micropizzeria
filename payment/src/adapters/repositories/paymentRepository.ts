import { PaymentDetails, Payment } from '../../domain/models/payment';

export class PaymentRepository {
  createPayment(paymentDetails: PaymentDetails): Payment {
    return new Payment(paymentDetails);
  }
}
