import { PaymentDetails, Payment } from '../models/payment';
import { PaymentRepository } from '../../adapters/repositories/paymentRepository';
import { AdyenService } from '../../adapters/services/adyenService';

export class PaymentService {
  private paymentRepository: PaymentRepository;
  private adyenService: AdyenService;

  constructor(paymentRepository: PaymentRepository, adyenService: AdyenService) {
    this.paymentRepository = paymentRepository;
    this.adyenService = adyenService;
  }

  async initiatePayment(paymentDetails: PaymentDetails) {
    const payment = await this.paymentRepository.createPayment(paymentDetails);
    const response = await this.adyenService.processPayment(payment);
    return response;
  }
}
